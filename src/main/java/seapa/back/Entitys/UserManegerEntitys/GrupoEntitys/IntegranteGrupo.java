package seapa.back.Entitys.UserManegerEntitys.GrupoEntitys;


import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "SEAPA_LISTA_GRUPO")
@Data
public class IntegranteGrupo {

    @EmbeddedId
    private Key key = new Key();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("grupoId")
    @JoinColumn(name = "grupo_id")
    Grupo grupo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @MapsId("integranteId")
    @JoinColumn(name = "integrante_id")
    ContaUsuario integrante;

    @Data
    @Embeddable
    public static class Key implements Serializable {
        Long grupoId;

        Long integranteId;
    }
}
