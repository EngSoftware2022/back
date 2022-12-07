package seapa.back;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seapa.back.Controllers.UserManagerController.ExtratoController;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Repository.UserManagerRepository.ContaUsuarioRepository;
import seapa.back.Repository.UserManagerRepository.ExtratoRepository;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestesExtrato {

    @Autowired
    private ContaUsuarioRepository repository;

    @Autowired
    private ExtratoController controller;

    public Extrato extrato;

    @BeforeEach
    public void beforeEach() {
        ContaUsuario contaUsuario = new ContaUsuario();
        contaUsuario.setNomeUsuario("xpto");
        contaUsuario.setEmail("xpto");
        contaUsuario.setSenha("xpto");
        contaUsuario.setStatusConta("xpto");
        this.extrato = new Extrato();
        this.extrato.setSaldoAtual(new BigDecimal(100));
        contaUsuario.setExtrato(this.extrato);
        this.repository.save(contaUsuario);
    }

    @Test
    public void testaVazio(){
        Extrato extrato =  controller.getSaldo(2L);
        assertTrue(Objects.isNull(extrato));
    }

    @Test
    public void testaSaldo(){
       // ExtratoController controller = Mockito.mock(ExtratoController.class);
        Extrato extrato =  controller.getSaldo(1L);
        BigDecimal bigDecimal = new BigDecimal(100.00).setScale(2);
        assertEquals(extrato.getSaldoAtual(), bigDecimal);
    }
}
