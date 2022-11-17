package seapa.back.Entitys.UserManegerEntitys;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "SEAPA_CONTA_USUARIO")
@Data
public class ConviteAmizade {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String dataAniversario;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String telefone;
}
