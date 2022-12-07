package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ConviteAmizadeController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ConviteAmizadeRepository;

@SpringBootTest
public class ConviteAmizadeTest {

    @Autowired
    private ConviteAmizadeController controller;

    @Autowired
    private ConviteAmizadeRepository repository;

    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    ContaUsuario contaUsuario1 = new ContaUsuario();
    ContaUsuario contaUsuario2 = new ContaUsuario();

    @BeforeEach
    public void beforeEach(){
        contaUsuario1.setId(1L);
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
        contaUsuarioRepository.save(contaUsuario2);
    }



}
