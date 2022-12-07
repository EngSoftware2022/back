package seapa.back.Entitys.UserManegerEntitys.UserEntitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seapa.back.Entitys.UserManegerEntitys.ClassId.ListaAmigosId;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_LISTA_AMIGOS")
@NamedQueries(value = {
        @NamedQuery(name = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID, query = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID),
        @NamedQuery(name = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID, query = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ListaAmigosId.class)
public class ListaAmigos {

    public static final String FIND_ALL_AMIGOS_BY_USUARIO_ID = "SELECT la FROM ListaAmigos la WHERE la.usuario.id = :usuarioId";
    public static final String FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID = "SELECT la FROM ListaAmigos la WHERE la.usuario.id = :usuarioId AND la.amigo.id = :amizadeId";

    @Id
    @OneToOne
    @JoinColumn(name = "conta_usuario_id", referencedColumnName = "id")
    private ContaUsuario usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "conta_amigo_id", referencedColumnName = "id")
    private ContaUsuario amigo;

}
