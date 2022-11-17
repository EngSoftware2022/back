package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_LISTA_AMIGOS")
@Data
public class ListaAmigos {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long usuarioId;

    @Column
    private Long amizadeId;

}
