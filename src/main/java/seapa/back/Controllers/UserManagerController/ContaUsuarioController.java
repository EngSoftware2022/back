package seapa.back.Controllers.UserManagerController;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Usuario;
import seapa.back.Models.DTOs.ContaUsuarioDTO;
import seapa.back.Models.UsuarioModel;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Services.UserManagerService.ContaUsuarioService;
import seapa.back.Settings.Mappers.ContaUsuarioMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/contasUsuarios")
public class ContaUsuarioController {

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ContaUsuarioService contaUsuarioService;

    @Autowired
    ContaUsuarioMapper contaUsuarioMapper;

    @GetMapping
    public List<ContaUsuario> findAllContasUsuarios() {
        return contaUsuarioRepository.findAll();
    }

    @GetMapping(value = "/id")
    public ResponseEntity<ContaUsuarioDTO> findContaUsuarioById(@RequestParam Long id) {
        ContaUsuario contaUsuario = contaUsuarioRepository.findById(id).get();
        ContaUsuarioDTO contaUsuarioDTO = contaUsuarioMapper.toContaUsuarioDTO(contaUsuario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(contaUsuarioDTO);
    }
    
    @GetMapping(value = "/nomeUsuario")
    public ResponseEntity<Optional<List<ContaUsuarioDTO>>> findAllContasUsuariosByNomeUsuarioLike(@RequestParam String nomeUsuario) {
        List<ContaUsuario> contasUsuarios = contaUsuarioService.findContaUsuarioByNomeUsuario(nomeUsuario);
        Optional<List<ContaUsuarioDTO>> contasUsuariosDTO = Optional.ofNullable(contaUsuarioMapper.toContaUsuarioDTOList(contasUsuarios));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(contasUsuariosDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertContaUsuario(@RequestBody UsuarioModel cadastroUsuario) {
        List<ContaUsuario> verificaSeexiste = contaUsuarioService.findContaUsuarioByNomeUsuario(cadastroUsuario.getNomeDeUsuario()) ;
        if (verificaSeexiste.size() == 1) {
            Usuario atualizaDados = verificaSeexiste.get(0).getUsuario();
            atualizaDados.atualizar(cadastroUsuario);
            verificaSeexiste.get(0).setUsuario(atualizaDados);
            contaUsuarioRepository.save(verificaSeexiste.get(0));
            return ;
        }
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
    @GetMapping(value = "/signin")
    public Long findByLogin(@ApiParam @RequestParam String usuario, @ApiParam @RequestParam String senha) {
        Long contaUsuarioId = contaUsuarioService.findContaUsuarioIdByNomeUsuarioAndSenha(usuario, senha);

        if (contaUsuarioId == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return contaUsuarioId;
    }
}
