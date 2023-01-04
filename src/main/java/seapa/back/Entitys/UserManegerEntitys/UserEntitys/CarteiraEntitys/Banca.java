package seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SEAPA_BANCA_USUARIO")
@SequenceGenerator(name = "banca_usuario_seq", sequenceName = "banca_usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class Banca extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banca_usuario_seq")
    private Long id;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToMany
    @JoinColumn(name = "banca_id")
    private List<MovimentacaoMonetaria> ultimasMovimentacoes;

}