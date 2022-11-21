package seapa.back.Controllers.UserManagerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Utils.StatusContaEnum;

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
        contaUsuario.setStatusConta(StatusContaEnum.ATIVO.toString());

        return contaUsuarioRepository.save(contaUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public HttpStatus deleteContaUsuario(@PathVariable Long id) {
        if (this.findContaUsuarioById(id) == null) {
            return HttpStatus.NOT_FOUND;
        }

        return HttpStatus.OK;
    }
}
