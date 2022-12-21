package seapa.back.Models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BancaUsuarioDTO {

    private Long id;

    private String nomeUsuario;

    private String saldo;
}
