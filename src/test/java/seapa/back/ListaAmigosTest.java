package seapa.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ListaAmigosController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ListaAmigosRepository;

@SpringBootTest
public class ListaAmigosTest {

    @Autowired
    ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    ListaAmigosController controller;

    @Autowired
    ListaAmigosRepository repository;

    ListaAmigos amigos = new ListaAmigos();

}
