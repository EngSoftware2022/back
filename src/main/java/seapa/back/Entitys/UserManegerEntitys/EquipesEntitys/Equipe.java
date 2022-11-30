package seapa.back.Entitys.UserManegerEntitys.EquipesEntitys;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SEAPA_EQUIPE_APOSTA")
@SequenceGenerator(name = "equipe_aposta_seq", sequenceName = "equipe_aposta_seq", allocationSize = 1, initialValue = 1)
@Data
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipe_aposta_seq")
    private Long id;

    @Column(name = "nome_equipe")
    private Date nome;

}
