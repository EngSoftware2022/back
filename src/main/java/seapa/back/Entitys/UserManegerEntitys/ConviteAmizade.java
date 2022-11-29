package seapa.back.Entitys.UserManegerEntitys;


import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SEAPA_CONVITE_AMIZADE")
@NamedQueries(value = {
        @NamedQuery(name = ConviteAmizade.FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID, query = ConviteAmizade.FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID)
})
@SequenceGenerator(name = "convite_amizade_seq", sequenceName = "convite_amizade_seq", allocationSize = 1, initialValue = 1)
@Data
public class ConviteAmizade implements Serializable {

    public static final String FIND_ALL_CONVITES_PENDENTES_BY_SOLICITANTE_ID = "SELECT ca FROM ConviteAmizade ca WHERE ca.solicitante.id = :solicitanteId AND ca.statusConvite = 'PENDENTE'";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "convite_amizade_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "solicitante_id", referencedColumnName = "id")
    private ContaUsuario solicitante;

    @OneToOne
    @JoinColumn(name = "solicitado_id", referencedColumnName = "id")
    private ContaUsuario solicitado;

    @Column(name = "data_solicitacao")
    private Date dataSolicitacao;

    @Column(name = "data_confirmacao")
    private Date dataConfirmacao;

    @Column(name = "status_convite", nullable = false)
    private String statusConvite;

}
