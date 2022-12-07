package seapa.back.Controllers.UserManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Repository.UserManagerRepository.ListaAmigosRepository;
import seapa.back.Services.UserManagerService.ListaAmigosService;

import java.util.List;

@RestController
@RequestMapping(value = "/listaAmigos")
public class ListaAmigosController {

    @Autowired
    private ListaAmigosRepository listaAmigosRepository;

    @Autowired
    private ListaAmigosService listaAmigosService;

    @GetMapping(value = "/usuario/{id}")
    public List<ListaAmigos> findAllAmigosByUsuarioId(@PathVariable Long id) {
        return listaAmigosService.findAllAmigosByUsuarioId(id);
    }

    @PostMapping
    public ListaAmigos insertNovoAmigo(@RequestBody ListaAmigos amigo) {
        return listaAmigosRepository.save(amigo);
    }

    @DeleteMapping(value = "/usuario/amigo/{id}")//ajustar
    public HttpStatus deleteAmigoById(@PathVariable Long id) {
        if (this.findAllAmigosByUsuarioId(id) == null) {
            return HttpStatus.NOT_FOUND;
        }

        return HttpStatus.OK;
    }
}
