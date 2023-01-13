package seapa.back.Entitys.BetManagerEntitys.ApostasUsuarioEntitys;

import lombok.Data;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Utils.StatusApostaUsuarioEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SEAPA_APOSTA_REALIZADA_USUARIO")
@Data
public class ApostaDoUsuario {

    @EmbeddedId
    private Key key = new Key();

    @ManyToOne
    @MapsId("apostaId")
    private Aposta aposta;

    @ManyToOne
    @MapsId("apostadorId")
    private ContaUsuario apostador;

    @Column(name = "opcao_escolhida")
    private String opcaoEscolhida;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_aposta_participante")
    private StatusApostaUsuarioEnum statusDaApostaDoParticipante;

    @Column(name = "lucro_obtido")
    private BigDecimal lucroObtido = BigDecimal.ZERO;

    @Data
    @Embeddable
    public static class Key implements Serializable {
        private Long apostaId;

        private Long apostadorId;
    }
}