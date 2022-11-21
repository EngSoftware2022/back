package seapa.back.Entitys.UserManegerEntitys;

import lombok.Data;
import seapa.back.Audit.Auditable;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_GRUPO")
@Data
public class Grupo extends Auditable<String> {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nomeGrupo;

    private Long moderadorId;

}
