package seapa.back.Models.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaAmigosResponse {

    private Long amigoId;

    private String amigoNomeUsuario;
}
