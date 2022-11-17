package seapa.back.Controllers.UserManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/contasUsuarios")
public class ContaUsuarioController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @GetMapping
    public List<ContaUsuario> findAllContasUsuarios() {
        return contaUsuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ContaUsuario findContaUsuarioById(@PathVariable Long id) {
        return contaUsuarioRepository.findById(id).get();
    }

    @PostMapping
    public ContaUsuario insertContaUsuario(@RequestBody ContaUsuario contaUsuario) {
        return contaUsuarioRepository.save(contaUsuario);
    }
}
