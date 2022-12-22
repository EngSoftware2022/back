package seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SEAPA_MOVIMENTACOES_BANCA_USUARIO")
@NamedQueries(value = {
        @NamedQuery(name = MovimentacaoMonetaria.FIND_ULTIMAS_CINCO_MOVIMENTACOES_MONETARIAS_USUARIO_BY_USUARIO_ID,
                query = MovimentacaoMonetaria.FIND_ULTIMAS_CINCO_MOVIMENTACOES_MONETARIAS_USUARIO_BY_USUARIO_ID)
})
@SequenceGenerator(name = "movimentacao_banca_seq", sequenceName = "movimentacao_banca_seq", allocationSize = 1, initialValue = 1)
@Data
public class MovimentacaoMonetaria extends Auditable<String> {

    public static final String FIND_ULTIMAS_CINCO_MOVIMENTACOES_MONETARIAS_USUARIO_BY_USUARIO_ID = "SELECT mm " +
            "FROM MovimentacaoMonetaria mm WHERE mm.banca.usuario.id = :usuarioId ORDER BY mm.dataUltimaModificacao DESC";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimentacao_banca_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name="banca_id")
    private Banca banca;

    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;

    @Column(name = "valor_movimentacao")
    private BigDecimal valorMovimentacao;
}
