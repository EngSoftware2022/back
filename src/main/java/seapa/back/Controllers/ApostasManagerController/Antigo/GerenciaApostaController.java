package seapa.back.Controllers.ApostasManagerController.Antigo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Controllers.UserManagerController.ExtratoController;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ParticipanteAposta;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Models.*;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.GerenciadorApostasRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/gerenciaAposta")
public class GerenciaApostaController {
    @Autowired
    private GerenciadorApostasRepository gerenciadorApostasRepository;
    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ApostasRepository aposta;

    @Autowired
    private ExtratoController extrato;

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping
    public ResponseEntity<Object> validarVencedores(@RequestBody ValidaApostaModel validaApostaModel){
        if(aposta.findById(validaApostaModel.getIdAposta()).isPresent()){
            BigDecimal valorAposta = BigDecimal.ZERO;
            int contador = 0;
            List<Long> idsUsuariosGanhadores = new ArrayList<>();
            List<ParticipanteAposta> ganhadores = new ArrayList<>();

            List<ParticipanteAposta> pAposta = new ArrayList<>();
            ApostaComum ap = aposta.findById(validaApostaModel.getIdAposta()).get();

            if(ap.getIdModerador() == validaApostaModel.getIdModerador()){
                for(int i = 0; i < ap.getApostadores().size(); i++){
                    pAposta.add(ap.getApostadores().get(i));
                }

                for(int i = 0; i < pAposta.size(); i++){
                    if(pAposta.get(i).getAposta() == validaApostaModel.getValorResultado()){
                        idsUsuariosGanhadores.add(pAposta.get(i).getIdApostador());
                        ganhadores.add(pAposta.get(i));
                        contador++;
                    }
                    valorAposta = valorAposta.add(pAposta.get(i).getValorAposta());
                }

                if(contador == 0 || contador == pAposta.size()){
                    for(int i = 0; i < pAposta.size(); i++){
                        BigDecimal valor = BigDecimal.ZERO;
                        Extrato extrato1 = new Extrato();
                        valor = valor.add(pAposta.get(i).getValorAposta());

                        extrato1 = extrato.getSaldo(pAposta.get(i).getIdApostador());

                        valor = valor.add(extrato1.getSaldoAtual());

                        AtualizaExtratoModel atualiza = new AtualizaExtratoModel();
                        atualiza.setNovoSaldo(valor);
                        atualiza.setUsuarioId(pAposta.get(i).getIdApostador());

                        extrato.atualizaSaldo(atualiza);
                    }
                } else {
                    BigDecimal casaRecebe = null;
                    BigDecimal valor = null;
                    BigDecimal divisor = new BigDecimal(idsUsuariosGanhadores.size());
                    BigDecimal multiplicador = new BigDecimal(0.1);

                    casaRecebe = valorAposta.multiply(multiplicador);

                    valorAposta = valorAposta.subtract(casaRecebe);

                    valor = valorAposta.divide(divisor, 2, RoundingMode.UP);

                    for(int i = 0; i < idsUsuariosGanhadores.size(); i++){
                        Extrato extrato1 = new Extrato();
                        extrato1 = extrato.getSaldo(idsUsuariosGanhadores.get(i));

                        valor = valor.add(extrato1.getSaldoAtual());

                        AtualizaExtratoModel atualiza = new AtualizaExtratoModel();
                        atualiza.setNovoSaldo(valor);
                        atualiza.setUsuarioId(pAposta.get(i).getIdApostador());

                        extrato.atualizaSaldo(atualiza);
                    }
                }

                ap.setVencedores(ganhadores);
                ap.setStatusAposta("Fechado");
                aposta.save(ap);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Object> insertGerenciadorAposta(@RequestBody CriarGerenciaAposta gerenciadorApostas) {
        Grupo grupo = timeRepository.findById(gerenciadorApostas.getGrupoId()).isPresent() ? timeRepository.findById(gerenciadorApostas.getGrupoId()).get() : null;

        if (grupo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        GerenciadorApostas gerenciaAposta = gerenciadorApostas.conversor(grupo);
        gerenciadorApostasRepository.save(gerenciaAposta);
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/editarGerenciaAposta")
    public ResponseEntity<Object> editarGerenciadorAposta(@RequestBody EditarGerenciaAposta editarGerenciaAposta) {
        if (Objects.equals(editarGerenciaAposta.getDataInicio(), new Date())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Grupo grupo = timeRepository.findById(editarGerenciaAposta.getGrupoId()).isPresent() ? timeRepository.findById(editarGerenciaAposta.getGrupoId()).get() : null;

        if (grupo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (Objects.equals(grupo.getModeradorId(), editarGerenciaAposta.getIdUsuario())) {
            GerenciadorApostas gerenciaApostaExistente = gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).isPresent() ? gerenciadorApostasRepository.findById(editarGerenciaAposta.getIdGerenciaAposta()).get() : null;

            if (gerenciaApostaExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            GerenciadorApostas gerenciaApostaAtualizado = editarGerenciaAposta.conversor(grupo);
            gerenciaApostaAtualizado.setId(gerenciaApostaExistente.getId());

            gerenciadorApostasRepository.save(gerenciaApostaAtualizado);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

