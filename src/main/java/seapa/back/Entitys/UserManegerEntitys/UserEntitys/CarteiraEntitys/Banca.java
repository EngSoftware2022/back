package seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "SEAPA_BANCA_USUARIO")
@Data
public class Banca extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @OneToMany(targetEntity = MovimentacaoMonetaria.class, mappedBy="banca", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<MovimentacaoMonetaria> ultimasMovimentacoes;

}