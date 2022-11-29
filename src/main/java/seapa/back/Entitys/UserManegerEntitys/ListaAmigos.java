package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "SEAPA_LISTA_AMIGOS")
@NamedQueries(value = {
        @NamedQuery(name = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID, query = ListaAmigos.FIND_ALL_AMIGOS_BY_USUARIO_ID),
        @NamedQuery(name = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID, query = ListaAmigos.FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID)
})
@Data
public class ListaAmigos {

    public static final String FIND_ALL_AMIGOS_BY_USUARIO_ID = "SELECT la FROM ListaAmigos la WHERE la.usuarioId = :usuarioId";
    public static final String FIND_UNIQUE_AMIGO_BY_USUARIO_ID_AND_AMIZADE_ID = "SELECT la FROM ListaAmigos la WHERE la.usuarioId = :usuarioId AND la.amizadeId = :amizadeId";

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long usuarioId;

    @Column
    private Long amizadeId;

}
