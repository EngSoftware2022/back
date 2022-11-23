package seapa.back.Entitys.UserManegerEntitys;

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
    private BigDecimal SaldoAtual;

    @Embedded
    private UltimasMovimentacoes extrato;
}
