package seapa.back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.ConviteGrupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.IntegrantesGrupo;
import seapa.back.Models.CriarGrupoModel;
import seapa.back.Repository.TeamManagerRepository.ConviteTimeRepository;
import seapa.back.Repository.TeamManagerRepository.IntegrantesTimeRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepositorioAuxiliar;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import java.util.List;

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

    @PostMapping
    public HttpStatus criarGrupo(CriarGrupoModel grupoModel){
        Grupo grupo = grupoModel.toGrupo();
        timeRepository.save(grupo);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/novomembro")
    public HttpStatus incluirPessoas(Long idGrupo, Long idConvidado){
       Grupo grupo = timeRepository.findById(idGrupo).get();
       ConviteGrupo conviteGrupo = new ConviteGrupo();
       conviteGrupo.setGrupo(grupo);
       conviteGrupo.setIdUsuarioConvidado(idConvidado);
       conviteGrupo.setNomeAdmGrupo(grupo.getNomeModerador());
       return HttpStatus.OK;
    }

    @GetMapping(value = "/listarmembros")
    public List<IntegrantesGrupo> listarMembros(Long idGrupo){
        return repositorioAuxiliar.findByGrupo(idGrupo);
    }

    @DeleteMapping()
    public HttpStatus deletarGrupo(Long idGrupo){
        timeRepository.deleteById(idGrupo);
        return HttpStatus.OK;
    }

    @GetMapping(value = "/listaradministracao")
    public List<Grupo> ListarOndeSouAdmin(Long idUsuario){
        return repositorioAuxiliar.findGruposOndeSouAdmin(idUsuario);
    }

    @GetMapping(value = "/listarparticipacao")
    public List<Grupo> ListarOndeSouMembro(Long idUsuario){
        return repositorioAuxiliar.findGruposOndeSouMembro(idUsuario);
    }

    @PostMapping(value = "/listarConvites")
    public List<ConviteGrupo> listaDeConvides(Long idUsuario){
        return repositorioAuxiliar.findConvitesDeGrupo(idUsuario);
    }

    @PostMapping(value = "/aceitarConvite")
    public HttpStatus aceitarConvite(Long idUsuario, boolean aceito){//terminar
        return HttpStatus.OK;
    }

    @DeleteMapping(value = "/deletarMembro")
    public HttpStatus retirarMembro(Long idUsuario, Long idGrupo){
        IntegrantesGrupo integrante = repositorioAuxiliar.findIntegranteByIdUsuario(idUsuario, idGrupo);
        integrantesRepository.delete(integrante);
        return HttpStatus.OK;
    }

}
