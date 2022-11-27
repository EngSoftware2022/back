package seapa.back.Controllers.UserManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.Usuario;
import seapa.back.Repository.UserManagerRepository.UsuarioRepository;

import java.util.List;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Usuario findUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id).get();
    }

    @PostMapping
    public Usuario insertUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
