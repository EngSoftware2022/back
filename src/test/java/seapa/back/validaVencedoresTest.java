package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.ApostasManagerController.Antigo.GerenciaApostaController;
import seapa.back.Controllers.UserManagerController.ExtratoController;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ApostaComum;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.ParticipanteAposta;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Models.ValidaApostaModel;
import seapa.back.Repository.ApostasManagerRepositosy.ApostasRepository;
import seapa.back.Repository.ApostasManagerRepositosy.ParticipanteApostaRepository;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ExtratoRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class validaVencedoresTest {
    @Autowired
    private ContaUsuarioRepository contaUsuarioRepository;

    @Autowired
    private ApostasRepository aposta;

    @Autowired
    private ParticipanteApostaRepository participanteApostaRepository;

    @Autowired
    private ExtratoRepository ext;

    @Autowired
    private ExtratoController controller;

    @Autowired
    private GerenciaApostaController apostaController;

    ContaUsuario contaUsuario1 = new ContaUsuario();
    ContaUsuario contaUsuario2 = new ContaUsuario();

    Extrato extrato = new Extrato();
    Extrato extrato1 = new Extrato();

    ApostaComum ap = new ApostaComum();

    ParticipanteAposta pa1 = new ParticipanteAposta();
    ParticipanteAposta pa2 = new ParticipanteAposta();


    @BeforeEach
    public void beforeEach(){
        contaUsuario1.setId(1L);
        contaUsuario1.setNomeUsuario("user1");
        contaUsuario1.setEmail("juao");
        contaUsuario1.setSenha("juao");
        contaUsuario1.setStatusConta("juao");
        extrato.setSaldoAtual(new BigDecimal(100));
        ext.save(extrato);
        contaUsuario1.setExtrato(extrato);

        contaUsuario2.setId(2L);
        contaUsuario2.setNomeUsuario("user2");
        contaUsuario2.setEmail("maria");
        contaUsuario2.setSenha("maria");
        contaUsuario2.setStatusConta("maria");
        extrato1.setSaldoAtual(new BigDecimal(50));
        ext.save(extrato1);
        contaUsuario2.setExtrato(extrato1);

        contaUsuarioRepository.save(contaUsuario1);
        contaUsuarioRepository.save(contaUsuario2);

        pa1.setId(1L);
        pa1.setIdApostador(1L);
        pa1.setValorAposta(new BigDecimal(50));
        pa1.setAposta(true);
        pa1.setIdAposta(1L);

        pa2.setId(2L);
        pa2.setIdApostador(2L);
        pa2.setValorAposta(new BigDecimal(50));
        pa2.setAposta(false);
        pa2.setIdAposta(1L);

        participanteApostaRepository.save(pa1);
        participanteApostaRepository.save(pa2);

        List<ParticipanteAposta> apostadores = new ArrayList<>();
        apostadores.add(pa1);
        apostadores.add(pa2);

        ap.setId(1L);
        ap.setStatusAposta("Em andamento");
        ap.setIdModerador(3L);
        ap.setApostadores(apostadores);

        aposta.save(ap);

    }

    @Test
    public void validaSaldo(){
        ValidaApostaModel apostaModel = new ValidaApostaModel();
        apostaModel.setIdAposta(1L);
        apostaModel.setValorResultado(true);
        apostaModel.setIdModerador(3L);

        apostaController.validarVencedores(apostaModel);

        Extrato extrato = controller.getSaldo(1L);
        BigDecimal bigDecimal = new BigDecimal(190.00).setScale(2);
        assertEquals(extrato.getSaldoAtual(), bigDecimal);
    }

    @Test
    public void validaStatus(){
        ValidaApostaModel apostaModel = new ValidaApostaModel();
        apostaModel.setIdAposta(1L);
        apostaModel.setValorResultado(true);
        apostaModel.setIdModerador(3L);

        apostaController.validarVencedores(apostaModel);

        ApostaComum ap1 = aposta.findById(1L).get();

        assertEquals(ap1.getStatusAposta(), "Fechado");
    }
}
