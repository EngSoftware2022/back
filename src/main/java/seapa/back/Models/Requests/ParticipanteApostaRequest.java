package seapa.back.Models.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipanteApostaRequest {

    private Long apostaId;

    private Long apostadorId;

    private String opcaoEscolhida;

}
