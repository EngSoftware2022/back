package seapa.back.Models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seapa.back.Utils.StatusApostaUsuarioEnum;
import seapa.back.Utils.StatusDaApostaEnum;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApostaDoUsuarioDTO {

    private Long apostaGerenciadorId;

    private String apostaGerenciadorTipoEsporte;

    private String DescricaoAposta;

    private Date apostaDataInicio;

    private Date apostaDataFim;

    private String opcaoEscolhida;

    private BigDecimal valorAposta;

    private StatusDaApostaEnum statusAposta;

    private StatusApostaUsuarioEnum statusDaApostaDoParticipante;

}
