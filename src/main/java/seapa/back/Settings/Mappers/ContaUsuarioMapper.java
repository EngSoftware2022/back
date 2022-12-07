package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;
import seapa.back.Models.ContaUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ContaUsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ContaUsuarioDTO toContaUsuarioDTO(ContaUsuario contaUsuario) {
        return modelMapper.map(contaUsuario, ContaUsuarioDTO.class);
    }

    public List<ContaUsuarioDTO> toContaUsuarioDTOList(List<ContaUsuario> contaUsuarioList) {
        return contaUsuarioList.stream()
                .map(this::toContaUsuarioDTO)
                .collect(Collectors.toList());
    }
}
