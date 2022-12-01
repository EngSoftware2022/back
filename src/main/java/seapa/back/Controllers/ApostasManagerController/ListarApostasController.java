package seapa.back.Controllers.ApostasManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.GerenciadorApostasRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/listarApostas")
public class ListarApostasController {

    @Autowired
    private ApostasRepository listarApostas;

    @Autowired
    private GerenciadorApostasRepository gerenciadorApostas;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/grupo/{grupo_ID}")
    public List<ApostaComum> getAllApostas(@PathVariable Long grupo_ID){
        GerenciadorApostas gerAp = gerenciadorApostas.findById(grupo_ID).get();
        List<ApostaComum> apostas = gerAp.getApostas();

        /*
        List<Long> ids = null;

        for(int i = 0; i < gerAp.getApostas().size(); i++){
            ids.add(gerAp.getApostas().get(i).getId());
        }

        List<ApostaComum> apostas = null;
        for(int i = 0; i < ids.size(); i++){
            apostas.add(listarApostas.findById(ids.get(i)).get());
        }*/

        return apostas;
    }
}