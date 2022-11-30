package seapa.back.Entitys.UserManegerEntitys.ApostasEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seapa.back.Entitys.UserManegerEntitys.ClassId.ParticipanteApostaId;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_PARTICIPANTE_APOSTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ParticipanteApostaId.class)
public class ParticipanteAposta {

    @Id
    @ManyToOne
    @JoinColumn(name = "aposta_comum_id")
    private ApostaComum aposta;

    @Id
    @OneToOne
    @JoinColumn(name = "conta_usuario_id")
    private ContaUsuario participanteAposta;

    @Column(name = "lado_escolhido")
    private String ladoEscolhido;

    @Column(name = "ganhou")
    private Boolean ganhou;
}
