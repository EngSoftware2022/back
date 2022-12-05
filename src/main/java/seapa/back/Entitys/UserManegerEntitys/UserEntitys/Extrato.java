package seapa.back.Entitys.UserManegerEntitys.UserEntitys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "SEAPA_EXTRATO_USUARIO")
public class Extrato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "extrato_seq")
    private Long id;

    @Column
    private BigDecimal SaldoAtual = BigDecimal.ZERO;

    @Embedded
    private UltimasMovimentacoes extrato;

    public void atualizaExtrato(BigDecimal novoSaldo) {
        extrato.setSaldo5(extrato.getSaldo4());
        extrato.setSaldo4(extrato.getSaldo3());
        extrato.setSaldo3(extrato.getSaldo2());
        extrato.setSaldo2(extrato.getSaldo1());
        extrato.setSaldo1(this.getSaldoAtual());
        this.setSaldoAtual(novoSaldo);
    }
}
