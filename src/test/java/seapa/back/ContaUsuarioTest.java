package seapa.back;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ContaUsuarioController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;

@SpringBootTest
public class ContaUsuarioTest {

    @Autowired
    private ContaUsuarioRepository repository;

    @Autowired
    private ContaUsuarioController controller;

    ContaUsuario contaUsuario = new ContaUsuario();

}
