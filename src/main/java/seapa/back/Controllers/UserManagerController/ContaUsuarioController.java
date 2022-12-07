package seapa.back.Controllers.UserManagerController;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Models.UsuarioModel;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Services.UserManagerService.ContaUsuarioService;

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
        return contaUsuarioRepository.findById(id).orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertContaUsuario(@RequestBody UsuarioModel cadastroUsuario) {
        try{
            ContaUsuario conta = cadastroUsuario.conversor();
            contaUsuarioRepository.save(conta);
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
    public Long findByLogin(@ApiParam @PathVariable String usuario, @ApiParam @PathVariable String senha) {
        Long contaUsuarioId = contaUsuarioService.findContaUsuarioIdByNomeUsuarioAndSenha(usuario, senha);

        //if (contaUsuarioId == null) { JA ESTA RETORNANDO NORESULTEXCEPTION
            //throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        //}

        return contaUsuarioId;
    }
}
