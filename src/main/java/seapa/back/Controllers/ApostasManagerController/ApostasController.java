package seapa.back.Controllers.ApostasManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;
import seapa.back.Repository.UserManagerRepository.UsuarioRepository;

@RestController
@RequestMapping(value = "/Apostas")
public class ApostasController {

    @Autowired
    private ApostasRepository apostasRepository;

    @Autowired
    private TimeRepository timeRepository;

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{apostaID}&{grupoID}&{userID}")
    public void deleteAposta(@PathVariable Long apostaID, @PathVariable Long grupoID, @PathVariable Long userID){
        if(timeRepository.findById(grupoID).get().getModeradorId() == userID){
            if((apostasRepository.findById(apostaID).get().getStatusAposta() == "Encerrada") || (apostasRepository.findById(apostaID).get().getStatusAposta() == "Não iniciada")){
                apostasRepository.deleteById(apostaID);
            }
        }
    }

}
