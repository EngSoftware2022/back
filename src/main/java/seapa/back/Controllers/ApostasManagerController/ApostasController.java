package seapa.back.Controllers.ApostasManagerController;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.CentralDeGerenciamentoDasApostas;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.OpcoesAposta;
import seapa.back.Entitys.BetManagerEntitys.ApostasUsuarioEntitys.ApostaDoUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Models.DTOs.ApostaDTO;
import seapa.back.Models.Requests.ApostaRequest;
import seapa.back.Models.Requests.OpcoesApostaRequest;
import seapa.back.Models.Requests.ParticipanteApostaRequest;
import seapa.back.Repository.ApostasManagerRepositosy.ApostaUsuarioRepository;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.CentralDeGerenciamentoDeApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.OpcoesApostaRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepositorioAuxiliar;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Services.BetManagerService;
import seapa.back.Settings.Mappers.ApostasMapper;
import seapa.back.Settings.Mappers.CentralDeGerenciamentoDeApostasMapper;
import seapa.back.Utils.StatusApostaUsuarioEnum;
import seapa.back.Utils.StatusDaApostaEnum;
import seapa.back.Utils.TiposDeApostasEnum;
import seapa.back.Utils.TiposDeGerenciamentoEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/apostas")
public class ApostasController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private CentralDeGerenciamentoDeApostasMapper centralDeGerenciamentoDeApostasMapper;

    @Autowired
    private CentralDeGerenciamentoDeApostasRepository centralDeGerenciamentoDeApostasRepository;

    @Autowired
    private ApostasRepository apostasRepository;

    @Autowired
    private ApostaUsuarioRepository apostaUsuarioRepository;

    @Autowired
    private OpcoesApostaRepository opcoesApostaRepository;

    @Autowired
    private TimeRepositorioAuxiliar repositorioAuxiliar;

    @Autowired
    private BetManagerService apostaService;

    @Autowired
    private ApostasMapper apostasMapper;

    @PostMapping(value = "/novaAposta")
    public ResponseEntity<HttpStatus> criarNovaAposta(@RequestBody ApostaRequest parametrosAposta) {
        CentralDeGerenciamentoDasApostas gerenciadorApostas = Optional.of(centralDeGerenciamentoDeApostasRepository.findById(parametrosAposta.getCentralApostasId()).get()).orElseThrow(() -> new RuntimeException("Central de apostas não cadastrada!"));

        Aposta novaAposta = new Aposta();

        novaAposta.setGerenciador(gerenciadorApostas);
        novaAposta.setDescricaoAposta(parametrosAposta.getDescricaoAposta());
        novaAposta.setDataInicio(parametrosAposta.getDataInicio());
        novaAposta.setDataFim(parametrosAposta.getDataFim());

        if (parametrosAposta.getTipoGerenciamento().equalsIgnoreCase(TiposDeGerenciamentoEnum.AUTOGERENCIAVEL.toString())) {
            novaAposta.setTipoGerenciamento(TiposDeGerenciamentoEnum.AUTOGERENCIAVEL);
        } else {
            novaAposta.setTipoGerenciamento(TiposDeGerenciamentoEnum.API_EXTERNA);
        }

        if (parametrosAposta.getTipoAposta().equalsIgnoreCase(TiposDeApostasEnum.BISTATE.toString())) {
            novaAposta.setTipoAposta(TiposDeApostasEnum.BISTATE);
        } else {
            novaAposta.setTipoAposta(TiposDeApostasEnum.RANGE);
        }

        List<OpcoesAposta> opcoesAposta = Lists.newArrayList();

        for (OpcoesApostaRequest opcoesApostaRequest : parametrosAposta.getOpcoesAposta()) {
            OpcoesAposta opcaoAposta = new OpcoesAposta();

            opcaoAposta.setAposta(novaAposta);
            opcaoAposta.setPrimeiraOpcao(opcoesApostaRequest.getPrimeiraOpcao());
            opcaoAposta.setSegundaOpcao(opcoesApostaRequest.getSegundaOpcao());

            opcoesAposta.add(opcaoAposta);
        }

        novaAposta.setOpcoesApostas(opcoesAposta);
        novaAposta.setValorAposta(parametrosAposta.getValorAposta());
        novaAposta.setStatusAposta(StatusDaApostaEnum.AGUARDANDO_INICIO);

        gerenciadorApostas.getApostas().add(novaAposta);

        apostasRepository.save(novaAposta);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletarApostaExistente(@RequestParam Long apostaId) {
        Aposta aposta = Optional.of(apostasRepository.findById(apostaId).get()).orElseThrow(() -> new RuntimeException("Apostas não cadastrada!"));

        apostasRepository.delete(aposta);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/apostar")
    public ResponseEntity<HttpStatus> adicionarNovoApostaUsuario(@RequestBody ParticipanteApostaRequest apostaUsuario) {
        Aposta aposta = Optional.of(apostasRepository.findById(apostaUsuario.getApostaId()).get()).orElseThrow(() -> new RuntimeException("Apostas não cadastrada!"));
        ContaUsuario usuario = Optional.of(contaUsuarioRepository.findById(apostaUsuario.getApostadorId()).get()).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        ApostaDoUsuario novaApostaUsuario = new ApostaDoUsuario();

        novaApostaUsuario.setAposta(aposta);
        novaApostaUsuario.setApostador(usuario);
        novaApostaUsuario.setOpcaoEscolhida(apostaUsuario.getOpcaoEscolhida());
        novaApostaUsuario.setStatusDaApostaDoParticipante(StatusApostaUsuarioEnum.AGUARDANDO_INICIO);

        BigDecimal novoSaldoUsuario = usuario.getBanca().getSaldo().subtract(aposta.getValorAposta());

        usuario.getBanca().setSaldo(novoSaldoUsuario);

        aposta.getApostadores().add(novaApostaUsuario);

        contaUsuarioRepository.save(usuario);
        apostaUsuarioRepository.save(novaApostaUsuario);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(value = "/validarAposta")
    public ResponseEntity<HttpStatus> validarAposta(@RequestParam Long apostaId, @RequestParam Long opcaoVencedoraId, @RequestParam(required = false) String opcaoVencedoraString) {
        Aposta aposta = Optional.of(apostasRepository.findById(apostaId).get()).orElseThrow(() -> new RuntimeException("Apostas não cadastrada!"));
        OpcoesAposta opcaoVencedora = Optional.of(opcoesApostaRepository.findById(opcaoVencedoraId).get()).orElseThrow(() -> new RuntimeException("Opção não encontrada!"));

        aposta.setOpcaoVencedora(opcaoVencedora);

        List<ApostaDoUsuario> vencedoresDaAposta = Lists.newArrayList();

        for(ApostaDoUsuario apostaDoUsuario : aposta.getApostadores()){
            if(aposta.getTipoAposta().equals(TiposDeApostasEnum.BISTATE)){
                if(apostaDoUsuario.getOpcaoEscolhida().equals(opcaoVencedoraString)){
                    apostaDoUsuario.setStatusDaApostaDoParticipante(StatusApostaUsuarioEnum.VENCEDORA);

                    vencedoresDaAposta.add(apostaDoUsuario);
                }
                else {
                    apostaDoUsuario.setStatusDaApostaDoParticipante(StatusApostaUsuarioEnum.PERDEDORA);
                }
            }
            else if(aposta.getTipoAposta().equals(TiposDeApostasEnum.RANGE)){
                if(Long.parseLong(apostaDoUsuario.getOpcaoEscolhida()) == opcaoVencedoraId){
                    apostaDoUsuario.setStatusDaApostaDoParticipante(StatusApostaUsuarioEnum.VENCEDORA);

                    vencedoresDaAposta.add(apostaDoUsuario);
                }
                else {
                    apostaDoUsuario.setStatusDaApostaDoParticipante(StatusApostaUsuarioEnum.PERDEDORA);
                }
            }
        }

        Integer qtdApostadores = aposta.getApostadores().size();

        BigDecimal valorTotalApostasComputadas = aposta.getValorAposta().multiply(BigDecimal.valueOf(qtdApostadores));
        BigDecimal valorGanhoPelosUsuarios = valorTotalApostasComputadas.divide(BigDecimal.valueOf(vencedoresDaAposta.size()));

        for (ApostaDoUsuario usuarioVencedorAposta : vencedoresDaAposta) {
            BigDecimal novoSaldoUsuario = usuarioVencedorAposta.getApostador().getBanca().getSaldo().add(valorGanhoPelosUsuarios);

            usuarioVencedorAposta.setLucroObtido(valorGanhoPelosUsuarios);
            usuarioVencedorAposta.getApostador().getBanca().setSaldo(novoSaldoUsuario);

            aposta.getVencedores().add(usuarioVencedorAposta);
        }

        apostasRepository.save(aposta);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "/todasApostasCadastradas")
    public ResponseEntity<Optional<List<ApostaDTO>>> listarApostasPorGrupo(@RequestParam Long gropoId) {
        List<Aposta> apostasEntity = apostaService.findApostasByGrupo(gropoId);
        Optional<List<ApostaDTO>> apostasDTO = Optional.ofNullable(apostasMapper.toApostaDTOList(apostasEntity));

        return ResponseEntity.status(HttpStatus.OK).body(apostasDTO);
    }
}
