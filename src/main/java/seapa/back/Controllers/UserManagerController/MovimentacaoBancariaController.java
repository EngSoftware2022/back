package seapa.back.Controllers.UserManagerController;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.Banca;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.MovimentacaoMonetaria;
import seapa.back.Models.DTOs.BancaUsuarioDTO;
import seapa.back.Models.DTOs.MovimentacaoMonetariaDTO;
import seapa.back.Repository.UserManagerRepository.BancaRepository;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.MovimentacaoMonetariaRepository;
import seapa.back.Services.UserManagerService.ContaUsuarioService;
import seapa.back.Settings.Mappers.BancaMapper;
import seapa.back.Settings.Mappers.MovimentacaoMonetariaMapper;
import seapa.back.Utils.TiposMovimentacaoBancariaEnum;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carteira")
public class MovimentacaoBancariaController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ContaUsuarioService contaUsuarioService;

    @Autowired
    private BancaRepository bancaRepository;

    @Autowired
    private MovimentacaoMonetariaRepository movimentacaoMonetariaRepository;

    @Autowired
    BancaMapper bancaMapper;

    @Autowired
    MovimentacaoMonetariaMapper movimentacaoMonetariaMapper;

    @GetMapping(value = "/saldo")
    public ResponseEntity<BancaUsuarioDTO> saldoAtualDoUsuarioByUsuarioId(@RequestParam Long usuarioId){
        Optional<Banca> bancaUsuario = Optional.ofNullable(contaUsuarioRepository.findById(usuarioId).get().getBanca());

        if(bancaUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BancaUsuarioDTO bancaUsuarioDTO = bancaMapper.toBancaUsuarioDTO(bancaUsuario.get());

        return ResponseEntity.status(HttpStatus.OK).body(bancaUsuarioDTO);
    }

    @GetMapping(value = "/extrato/todasAsMovimentacoes")
    public ResponseEntity<List<MovimentacaoMonetariaDTO>> extratoDeTodasAsMovimentacoesDoUsuarioByUsuarioId(@RequestParam Long usuarioId){
        List<MovimentacaoMonetaria> movimentacoesMonetarias = contaUsuarioRepository.findById(usuarioId).get().getBanca().getUltimasMovimentacoes();

        if(movimentacoesMonetarias.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Collections.sort(movimentacoesMonetarias);

        List<MovimentacaoMonetariaDTO> movimentacaoMonetariaDTOList = movimentacaoMonetariaMapper.toMovimentacaoMonetariaDTOList(movimentacoesMonetarias);

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoMonetariaDTOList);
    }

    @GetMapping(value = "/extrato/ultimasMovimentacoes")
    public ResponseEntity<List<MovimentacaoMonetariaDTO>> extratoDasUltimasMovimentacoesDoUsuarioByUsuarioId(@RequestParam Long usuarioId){
        List<MovimentacaoMonetaria> allMovimentacoesMonetarias = contaUsuarioRepository.findById(usuarioId).get().getBanca().getUltimasMovimentacoes();

        if(allMovimentacoesMonetarias.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Collections.sort(allMovimentacoesMonetarias);

        List<MovimentacaoMonetaria> ultimasCincoMovimentacoesMonetarias = Lists.newArrayList();

        for(int contador = 0; contador < 5; contador ++){
            ultimasCincoMovimentacoesMonetarias.add(allMovimentacoesMonetarias.get(contador));
        }

        List<MovimentacaoMonetariaDTO> movimentacaoMonetariaDTOList = movimentacaoMonetariaMapper.toMovimentacaoMonetariaDTOList(ultimasCincoMovimentacoesMonetarias);

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoMonetariaDTOList);
    }

    @PutMapping(value = "/deposito")
    public ResponseEntity<BigDecimal> depositoNaCarteiraDoUsuarioByUsuarioId(@RequestParam Long usuarioId, @RequestParam BigDecimal valorDeposito) {
        Optional<Banca> bancaUsuario = Optional.ofNullable(contaUsuarioRepository.findById(usuarioId).get().getBanca());

        if(bancaUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(valorDeposito.compareTo(new BigDecimal(0)) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bancaUsuario.get().getSaldo());
        }

        MovimentacaoMonetaria movimentacaoMonetaria = new MovimentacaoMonetaria();

        movimentacaoMonetaria.setBanca(bancaUsuario.get());
        movimentacaoMonetaria.setTipoMovimentacao(TiposMovimentacaoBancariaEnum.DEPOSITO.toString());
        movimentacaoMonetaria.setValorMovimentacao(valorDeposito);

        bancaUsuario.get().setSaldo(bancaUsuario.get().getSaldo().add(valorDeposito));

        movimentacaoMonetariaRepository.save(movimentacaoMonetaria);
        bancaRepository.save(bancaUsuario.get());

        return ResponseEntity.status(HttpStatus.OK).body(bancaUsuario.get().getSaldo());
    }

    @PutMapping(value = "/saque")
    public ResponseEntity<BigDecimal> saqueNaCarteiraDoUsuarioByUsuarioId(@RequestParam Long usuarioId, @RequestParam BigDecimal valorSaque) {
        Optional<Banca> bancaUsuario = Optional.ofNullable(contaUsuarioRepository.findById(usuarioId).get().getBanca());

        if(bancaUsuario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(bancaUsuario.get().getSaldo().compareTo(valorSaque) < 0 || valorSaque.compareTo(new BigDecimal(0)) < 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bancaUsuario.get().getSaldo());
        }

        MovimentacaoMonetaria movimentacaoMonetaria = new MovimentacaoMonetaria();

        movimentacaoMonetaria.setBanca(bancaUsuario.get());
        movimentacaoMonetaria.setTipoMovimentacao(TiposMovimentacaoBancariaEnum.SAQUE.toString());
        movimentacaoMonetaria.setValorMovimentacao(valorSaque);

        bancaUsuario.get().setSaldo(bancaUsuario.get().getSaldo().subtract(valorSaque));

        movimentacaoMonetariaRepository.save(movimentacaoMonetaria);
        bancaRepository.save(bancaUsuario.get());

        return ResponseEntity.status(HttpStatus.OK).body(bancaUsuario.get().getSaldo());
    }

}
