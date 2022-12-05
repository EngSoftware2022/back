package seapa.back.Controllers.UserManagerController;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Models.UsuarioModel;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Services.UserManagerService.ContaUsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/contasUsuarios")
public class ContaUsuarioController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ContaUsuarioService contaUsuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public void insertContaUsuario(@RequestBody UsuarioModel cadastroUsuario) {
        try{
            cadastroUsuario.setSenha(passwordEncoder.encode(cadastroUsuario.getSenha()));

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

    @GetMapping("/validarLogin")
    public ResponseEntity<Boolean> validarLogin(@RequestParam String nomeUsuario,
                                                @RequestParam String senha) {
        Optional<ContaUsuario> optContaUsuario = contaUsuarioRepository.findByNomeUsuario(nomeUsuario);

        if (optContaUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        ContaUsuario contaUsuario = optContaUsuario.get();
        boolean isValido = passwordEncoder.matches(senha, contaUsuario.getSenha());

        HttpStatus status = isValido ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(isValido);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping(value = "/signin/usuario={usuario}&senha={senha}")
    public Long findByLogin(@ApiParam @PathVariable String usuario, @ApiParam @PathVariable String senha) {
        Long contaUsuarioId = contaUsuarioService.findContaUsuarioIdByNomeUsuarioAndSenha(usuario, senha);

        if (contaUsuarioId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return contaUsuarioId;
    }
}
