package seapa.back.Controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.CentralDeGerenciamentoDasApostas;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.ConviteGrupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.IntegrantesGrupo;
import seapa.back.Models.CriarGrupoModel;
import seapa.back.Repository.ApostasManagerRepositosy.CentralDeGerenciamentoDeApostasRepository;
import seapa.back.Repository.TeamManagerRepository.ConviteTimeRepository;
import seapa.back.Repository.TeamManagerRepository.IntegrantesTimeRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepositorioAuxiliar;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/team")
public class TeamManagerController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ConviteTimeRepository conviteRepository;

    @Autowired
    private IntegrantesTimeRepository integrantesRepository;

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private TimeRepositorioAuxiliar repositorioAuxiliar;

    @Autowired
    private CentralDeGerenciamentoDeApostasRepository centralDeGerenciamentoDeApostasRepository;

    @PostMapping
    public HttpStatus criarGrupo(@RequestBody CriarGrupoModel grupoModel){
        Grupo grupo = grupoModel.toGrupo();
        String nomeModerador = repositorioAuxiliar.findNomeUsuarioById(grupoModel.getModeradorId());
        grupo.setNomeModerador(nomeModerador);
        IntegrantesGrupo integrantesGrupo = new IntegrantesGrupo();
        integrantesGrupo.setNomeUsuario(nomeModerador);
        integrantesGrupo.setIdUsuarioIntegrante(grupo.getModeradorId());
        grupo.setIntegrantes(integrantesGrupo);

        List<CentralDeGerenciamentoDasApostas> centralDeGerenciamentoDasApostas = Lists.newArrayList();
        grupo.setCentralDeGerenciamentoDasApostas(centralDeGerenciamentoDasApostas);

        timeRepository.save(grupo);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/novomembro")
    public HttpStatus incluirPessoas(@RequestParam Long idGrupo, @RequestParam Long idConvidado){
       Grupo grupo = timeRepository.findById(idGrupo).get();
       ConviteGrupo conviteGrupo = new ConviteGrupo();
       conviteGrupo.setGrupo(grupo.getId());
       conviteGrupo.setIdUsuarioConvidado(idConvidado);
       conviteRepository.save(conviteGrupo);
       //conviteGrupo.setNomeAdmGrupo(grupo.getNomeModerador());
       return HttpStatus.OK;
    }

    @GetMapping(value = "/listarmembros")
    public List<IntegrantesGrupo> listarMembros(@RequestParam Long idGrupo){
        Grupo grupo = Optional.ofNullable( repositorioAuxiliar.findByGrupo(idGrupo)).orElseThrow(() -> new RuntimeException("Id do grupo invalido"));
        return grupo.getIntegrantes();
    }

    @DeleteMapping()
    public HttpStatus deletarGrupo(@RequestParam Long idGrupo){
        timeRepository.deleteById(idGrupo);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/listaradministracao")
    public List<Grupo> ListarOndeSouAdmin(@RequestParam Long idUsuario){
        return repositorioAuxiliar.findGruposOndeSouAdmin(idUsuario);
    }

    @GetMapping(value = "/listarparticipacao")
    public List<Grupo> ListarOndeSouMembro(@RequestParam Long idUsuario){
        return repositorioAuxiliar.findGruposOndeSouMembro(idUsuario);
    }

    @GetMapping(value = "/listarConvites")
    public List<ConviteGrupo> listaDeConvides(@RequestParam Long idUsuario){
        List<ConviteGrupo> convites = repositorioAuxiliar.findConvitesDeGrupo(idUsuario);
        return convites;
    }

    @PostMapping(value = "/aceitarConvite")
    public HttpStatus aceitarConvite(@RequestParam Long idUsuario, @RequestParam Long idGrupo , @RequestParam boolean aceito){
        Grupo grupo = Optional.ofNullable( repositorioAuxiliar.findConviteGrupoEspecifico(idGrupo)).orElseThrow(() -> new RuntimeException("Id do grupo invalido"));
        String nomeUsuario = Optional.ofNullable( repositorioAuxiliar.findNomeUsuarioById(idUsuario) ).orElseThrow(()-> new RuntimeException("Id do usuario invalido"));

        if(aceito){
            IntegrantesGrupo novoIntegrante = new IntegrantesGrupo();
            novoIntegrante.setIdUsuarioIntegrante(idUsuario);
            novoIntegrante.setNomeUsuario(nomeUsuario);
            grupo.setIntegrantes(novoIntegrante);
            timeRepository.save(grupo);
        }
        ConviteGrupo conviteGrupo = repositorioAuxiliar.findConvitesDeGrupo(idGrupo, idUsuario);
        conviteRepository.delete(conviteGrupo);
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/deletarMembro")
    public HttpStatus retirarMembro(@RequestParam Long idUsuario, @RequestParam Long idGrupo){
        Grupo grupo = Optional.ofNullable( repositorioAuxiliar.findByGrupo(idGrupo)).orElseThrow(() -> new RuntimeException("Id do grupo invalido"));
        List<IntegrantesGrupo> integrantesGrupos = grupo.getIntegrantes().stream().filter( i -> i.getIdUsuarioIntegrante() == idUsuario).collect(Collectors.toList());
        if(integrantesGrupos.size() > 1){
            throw new RuntimeException("Filtro para exclusão falhou"); //nunca deveria entrar aqui
        }
        grupo.getIntegrantes().removeIf( i -> i.getIdUsuarioIntegrante() == idUsuario);
        timeRepository.save(grupo);
        integrantesRepository.delete(integrantesGrupos.get(0));
        return HttpStatus.OK;
    }

    @PutMapping(value = "/novaCentralDeGerenciamento")
    public ResponseEntity<HttpStatus> criarNovoCentroDeGerenciamentoDeApostas(@RequestParam Long grupoId, @RequestParam String tipoEsporte) {
        Grupo grupo = Optional.ofNullable(repositorioAuxiliar.findByGrupo(grupoId)).orElseThrow(() -> new RuntimeException("Id do grupo invalido"));

        CentralDeGerenciamentoDasApostas novaCentralDeGerenciamento = new CentralDeGerenciamentoDasApostas();

        novaCentralDeGerenciamento.setGrupo(grupo);
        novaCentralDeGerenciamento.setTipoEsporte(tipoEsporte);

        grupo.getCentralDeGerenciamentoDasApostas().add(novaCentralDeGerenciamento);

        centralDeGerenciamentoDeApostasRepository.save(novaCentralDeGerenciamento);
        timeRepository.save(grupo);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
