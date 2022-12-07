package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import seapa.back.Controllers.UserManagerController.ConviteAmizadeController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Models.ConviteAmizadeDTO;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ConviteAmizadeRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    ConviteAmizade conviteAmizade = new ConviteAmizade();

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
        conviteAmizade.setSolicitado(contaUsuario2);
        conviteAmizade.setSolicitante(contaUsuario1);
        conviteAmizade.setId(1L);
        conviteAmizade.setStatusConvite("PENDENTE");
        repository.save(conviteAmizade);
    }

    @Test
    public void conferindoSeConviteFoiEnviado(){
        ResponseEntity<List<ConviteAmizadeDTO>> REConvite =  controller.findAllConvitesPendentesByUsuarioId(1L);
        ConviteAmizadeDTO convite = REConvite.getBody().get(0);
        assertTrue(Objects.nonNull(convite));
        assertEquals(convite.getSolicitadoId(), 2L);
        assertEquals(convite.getSolicitanteId(), 1L);
    }

    @Test
    public void recusandoConviteDeAmizade(){
        ResponseEntity response = controller.atualizaStatusConviteAmizade(1L, "REJEITADO");
        String resposta = response.getBody().toString();
        assertEquals(resposta , "Convite de amizade rejeitado com SUCESSO");
    }

    @Test
    public void aceitandoConviteDeAmizade(){
        ResponseEntity response = controller.atualizaStatusConviteAmizade(1L, "ACEITO");
        String resposta = response.getBody().toString();
        assertEquals(resposta , "Convite de amizade aceito com SUCESSO");
    }

    @Test
    public void deletandoConviteDeAmizade(){
        ResponseEntity response = controller.deleteConviteAmizade(1L);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }




}
