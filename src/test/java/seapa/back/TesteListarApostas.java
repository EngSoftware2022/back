package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.ApostasManagerController.ListarApostasController;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.UserManegerEntitys.ApostasEntitys.GerenciadorApostas;
import seapa.back.Entitys.UserManegerEntitys.GrupoEntitys.Grupo;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.GerenciadorApostasRepository;
import seapa.back.Repository.TeamManagerRepository.TimeRepository;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TesteListarApostas {

    @Autowired
    private GerenciadorApostasRepository gerenciadorApostasRepository;

    @Autowired
    private TimeRepository time;

    @Autowired
    private ApostasRepository apostasRepository;

    @Autowired
    private ListarApostasController controller;

    Grupo gp = new Grupo();
    ApostaComum ap = new ApostaComum();
    ApostaComum ap1 = new ApostaComum();

    GerenciadorApostas gerAp = new GerenciadorApostas();


    @BeforeEach
    public void beforeEach(){
        /*   comentado para testar se vazio passa
        ap.setId(1L);
        ap1.setId(2L);
        apostasRepository.save(ap);
        apostasRepository.save(ap1);

        List<ApostaComum> apostas = new ArrayList<>();
        apostas.add(ap);
        apostas.add(ap1);*/

        gp.setId(1L);
        gp.setModeradorId(1L);
        gp.setNomeModerador("user1");
        gp.setNomeGrupo("Grupo Teste");
        time.save(gp);

        gerAp.setId(1L);
        //gerAp.setApostas(apostas);
        gerAp.setGrupo(gp);
        gerenciadorApostasRepository.save(gerAp);
    }

    @Test
    public void apostasVazia(){
        List<ApostaComum> apostas = controller.getAllApostas();
        assertTrue(Objects.isNull(apostas));
    }

    @Test
    public void apostasExistem(){
        List<ApostaComum> apostas = controller.getAllApostas();
        assertTrue(Objects.nonNull(apostas));
    }

    @Test
    public void testaApostaDaInterfaceVazio(){
        List<ApostaComum> apostas = controller.getApostasDaInterface(2L);
        assertTrue(Objects.isNull(apostas));
    }

    @Test
    public void testaApostaDaInterfaceExiste(){
        List<ApostaComum> apostas = controller.getApostasDaInterface(1L);
        assertTrue(Objects.nonNull(apostas));
    }

}
