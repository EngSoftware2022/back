package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.UsuarioController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Usuario;
import seapa.back.Repository.UserManagerRepository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;

    @Autowired
    private UsuarioRepository repository;

    Usuario usuario = new Usuario();

    @BeforeEach
    public void beforeEach(){
        usuario.setId(1L);
        usuario.setCpf("xpto");
        usuario.setNome("xpto");
        usuario.setDataAniversario(LocalDate.now());
        usuario.setSobrenome("xpto");
        usuario.setTelefone("xpto");
        repository.save(usuario);
    }

    @Test
    public void testeAcharTodos(){
        List<Usuario> usuarios = controller.findAllUsuarios();
        assertTrue(Objects.nonNull(usuarios));
    }

    @Test
    public void naoAcharPorId(){
        assertTrue(Objects.isNull(controller.findUsuarioById(2L)));
    }

    @Test
    public void AcharPorId(){
        Usuario usuario = controller.findUsuarioById(1L);
        assertTrue(Objects.nonNull(usuario));
        assertEquals(usuario.getNome(),"xpto");
        assertEquals(usuario.getCpf(),"xpto");
        assertEquals(usuario.getTelefone(),"xpto");
        assertEquals(usuario.getSobrenome(),"xpto");
        assertEquals(usuario.getDataAniversario(),LocalDate.now());
    }
}
