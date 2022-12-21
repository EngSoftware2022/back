package seapa.back.Models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Extrato;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.Usuario;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaUsuarioDTO {

    private Long id;

    private Usuario usuario;

    private String nomeUsuario;

    private String email;

    private String statusConta;

    private Extrato extrato;

}
