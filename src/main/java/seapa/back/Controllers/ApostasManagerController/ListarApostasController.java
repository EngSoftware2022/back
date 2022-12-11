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
    @GetMapping(value = "/interface/{interface_id}")
    public List<ApostaComum> getApostasDaInterface(@PathVariable Long interface_id){
        GerenciadorApostas gerAp = gerenciadorApostas.findById(interface_id).isPresent() ? gerenciadorApostas.findById(interface_id).get() : null;

        if(gerAp == null){
            return null;
        }

        List<ApostaComum> apostas = gerenciadorApostas.findById(interface_id).get().getApostas();

        return apostas;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ApostaComum> getAllApostas(){
        List<ApostaComum> apostas = listarApostas.findAll();

        if(apostas.size() == 0){
            return null;
        }

        return apostas;
    }
}