package seapa.back.Models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Login {

    @ApiModelProperty(example = "magnao")
    private String nomeDeUsuario;

    private String senha;
}
