package seapa.back.Controllers.UserManagerController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Models.Responses.ListaAmigosResponse;
import seapa.back.Repository.UserManagerRepository.ListaAmigosRepository;
import seapa.back.Services.UserManagerService.ListaAmigosService;
import seapa.back.Settings.Mappers.ListaDeAmigosMapper;

import java.util.List;

@RestController
@RequestMapping(value = "/listaAmigos")
public class ListaAmigosController {

    @Autowired
    private ListaAmigosRepository listaAmigosRepository;

    @Autowired
    private ListaAmigosService listaAmigosService;

    @Autowired
    ListaDeAmigosMapper listaDeAmigosMapper;

    @GetMapping(value = "/usuario")
    public ResponseEntity<List<ListaAmigosResponse>> findAllAmigosByUsuarioId(@RequestParam Long usuarioId) {
        List<ListaAmigos> listaAmigos = listaAmigosService.findAllAmigosByUsuarioId(usuarioId);
        List<ListaAmigosResponse> listaAmigosResponseList = listaDeAmigosMapper.toListaDeAmigosResponseList(listaAmigos);

        return ResponseEntity.status(HttpStatus.OK).body(listaAmigosResponseList);
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
