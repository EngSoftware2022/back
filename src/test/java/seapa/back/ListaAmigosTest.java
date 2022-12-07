package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ListaAmigosController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ListaAmigosRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ListaAmigosTest {

    @Autowired
    ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    ListaAmigosController controller;

    @Autowired
    ListaAmigosRepository repository;

   // ContaUsuario contaUsuario1 = new ContaUsuario();
    //ContaUsuario contaUsuario2 = new ContaUsuario();
    ListaAmigos amigos = new ListaAmigos();

    @BeforeEach
    public void beforeEach(){
        /*contaUsuario1.setId(1L);
        contaUsuario1.setNomeUsuario("user1");
        contaUsuario1.setEmail("juao");
        contaUsuario1.setSenha("juao");
        contaUsuario1.setStatusConta("juao");
        contaUsuario2.setId(2L);
        contaUsuario2.setNomeUsuario("user2");
        contaUsuario2.setEmail("maria");
        contaUsuario2.setSenha("maria");
        contaUsuario2.setStatusConta("maria");
        contaUsuarioRepository.save(contaUsuario1);
        contaUsuarioRepository.save(contaUsuario2);*/
        amigos.setAmizadeId(2L);
        amigos.setUsuarioId(1L);
        repository.save(amigos);
    }

    @Test
    public void achandoAmigosDoUsuario(){
       List<ListaAmigos> amigo = controller.findAllAmigosByUsuarioId(1L);
       assertEquals(amigo.get(0).getAmizadeId(),2L);
       assertEquals(amigo.get(0).getUsuarioId(), 1L);
    }

    @Test
    public void deletandoAmizade(){//fazer apos ajuste
    }
}
