package seapa.back.Entitys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOLICITACOES_DE_AMIZADE")
@Data
public class PedidosDeAmizade {

    @Id
    @GeneratedValue(generator = "SEQ_ATIVO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String usuarioSolicitante;

    @Column
    private String usuarioDestino;

    public PedidosDeAmizade(String usuarioSolicitante, String usuarioDestino) {
        this.usuarioSolicitante = usuarioSolicitante;
        this.usuarioDestino = usuarioDestino;
    }

    public PedidosDeAmizade() {
    }
}
