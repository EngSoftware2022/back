package seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SEAPA_MOVIMENTACOES_BANCA_USUARIO")
@Data
public class MovimentacaoMonetaria extends Auditable<String> implements Comparable<MovimentacaoMonetaria> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="banca_id")
    private Banca banca;

    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;

    @Column(name = "valor_movimentacao")
    private BigDecimal valorMovimentacao;

    @Override
    public int compareTo(MovimentacaoMonetaria outraMovimentacao) {
        if (this.dataUltimaModificacao.after(outraMovimentacao.getDataUltimaModificacao())) {
            return -1;
        }

        if (this.dataUltimaModificacao.before(outraMovimentacao.getDataUltimaModificacao())) {
            return 1;
        }

        return 0;
    }
}
