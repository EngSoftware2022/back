package seapa.back.Entitys.UserManegerEntitys.UserEntitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SEAPA_LISTA_AMIGOS")
@NamedQueries(value = {
        @NamedQuery(name = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID, query = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID),
        @NamedQuery(name = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID, query = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID)
})
@Data
public class ListaAmigos {

    public static final String FIND_ALL_AMIGOS_BY_USUARIO_ID = "SELECT la FROM ListaAmigos la WHERE la.usuario.id = :usuarioId";
    public static final String FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID = "SELECT la FROM ListaAmigos la WHERE la.usuario.id = :usuarioId AND la.amigo.id = :amizadeId";

    @JsonIgnore
    @EmbeddedId
    private Key key = new Key();

    @ManyToOne
    @MapsId("usuarioId")
    private ContaUsuario usuario;

    @ManyToOne
    @MapsId("amigoId")
    private ContaUsuario amigo;

    @Embeddable
    @Data
    public static class Key implements Serializable {
        private Long usuarioId;
        private Long amigoId;
    }

}
