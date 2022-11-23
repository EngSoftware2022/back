package seapa.back.Controllers.UserManagerController;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import seapa.back.Entitys.UserManegerEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Services.UserManagerService.ContaUsuarioService;
import seapa.back.Utils.StatusContaEnum;

import java.util.List;

@RestController
@RequestMapping(value = "/contasUsuarios")
public class ContaUsuarioController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ContaUsuarioService contaUsuarioService;

    @GetMapping
    public List<ContaUsuario> findAllContasUsuarios() {
        return contaUsuarioRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ContaUsuario findContaUsuarioById(@PathVariable Long id) {
        return contaUsuarioRepository.findById(id).get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertContaUsuario(@RequestBody ContaUsuario contaUsuario) {
        try{
            contaUsuario.setStatusConta(StatusContaEnum.ATIVO.toString());

            contaUsuarioRepository.save(contaUsuario);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void deleteContaUsuario(@PathVariable Long id) {
        if (this.findContaUsuarioById(id) == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        contaUsuarioRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/signin/usuario={usuario}&senha={senha}")
    public Long signIn(@ApiParam @PathVariable String usuario, @ApiParam @PathVariable String senha) {
        Long contaUsuarioId = contaUsuarioService.findContaUsuarioIdByNomeUsuarioAndSenha(usuario, senha);

        if (contaUsuarioId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return contaUsuarioId;
    }
}
