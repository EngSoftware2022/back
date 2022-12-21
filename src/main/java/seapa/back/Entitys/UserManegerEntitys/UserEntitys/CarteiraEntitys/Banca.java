package seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys;

import lombok.Data;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SEAPA_BANCA_USUARIO")
@SequenceGenerator(name = "banca_usuario_seq", sequenceName = "banca_usuario_seq", allocationSize = 1, initialValue = 1)
@Data
public class Banca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banca_usuario_seq")
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private ContaUsuario usuario;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO;

}