package seapa.back.Entitys.UserManegerEntitys.ApostasEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seapa.back.Entitys.UserManegerEntitys.ClassId.ParticipanteApostaId;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SEAPA_PARTICIPANTE_APOSTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteAposta {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "id_aposta", nullable = false)
    private Long idAposta;

    @Column(name = "id_apostador", nullable = false)
    private Long idApostador;

    @Column(name = "valor_aposta")
    private BigDecimal valorAposta;

    @Column(name = "aposta_user")
    private Boolean aposta;
}
