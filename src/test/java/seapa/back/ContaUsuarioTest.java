package seapa.back;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ContaUsuarioController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

import javax.persistence.NoResultException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ContaUsuarioTest {

    @Autowired
    private ContaUsuarioRepository repository;

    @Autowired
    private ContaUsuarioController controller;

    ContaUsuario contaUsuario = new ContaUsuario();

    @BeforeEach
    public void beforeAll(){
        contaUsuario.setId(1L);
        contaUsuario.setNomeUsuario("xpto");
        contaUsuario.setEmail("xpto");
        contaUsuario.setSenha("xpto");
        contaUsuario.setStatusConta("xpto");
        repository.save(contaUsuario);
    }

    @Test
    public void testaVazio(){
        ContaUsuario contaUsuario = controller.findContaUsuarioById(1L);
        assertTrue(Objects.isNull(contaUsuario));
    }

    @Test
    public void testaEncontrarUsuario(){
        ContaUsuario contaUsuario = controller.findContaUsuarioById(1L);
        assertTrue(!Objects.isNull(contaUsuario));
        assertEquals(contaUsuario.getNomeUsuario(), "xpto");
        assertEquals(contaUsuario.getSenha(), "xpto");
        assertEquals(contaUsuario.getEmail(), "xpto");
        assertEquals(contaUsuario.getStatusConta(), "xpto");
    }

    @Test
    public void acharPorLogin(){
        assertEquals(1L, controller.findByLogin("xpto", "xpto"));
    }

    @Test
    public void naoAcharPorLogin(){
        assertThrows(NoResultException.class, () -> controller.findByLogin("xxpto", "xpto"));
    }

    @Test
    public void naoValidarPorSenhaErrada(){
        assertThrows(NoResultException.class, () -> controller.findByLogin("xpto", "xxpto"));
    }

    @Test
    public void conferirExclusão(){
        controller.deleteContaUsuario(1l);
        assertTrue(Objects.isNull( controller.findContaUsuarioById(1L)) );
    }

}
