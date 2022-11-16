package seapa.back.Entitys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AMIGOS")
@Data
public class Amigos {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_id")
    private Usuarios user;

}
