package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.BetManagerEntitys.ApostasUsuarioEntitys.ApostaDoUsuario;
import seapa.back.Models.DTOs.ApostaDoUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ApostaDoUsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ApostaDoUsuarioDTO toApostaDoUsuarioDTO(ApostaDoUsuario apostaDoUsuario) {
        return modelMapper.map(apostaDoUsuario, ApostaDoUsuarioDTO.class);
    }

    public List<ApostaDoUsuarioDTO> toApostaDoUsuarioDTOList(List<ApostaDoUsuario> apostaDoUsuarioList) {
        return apostaDoUsuarioList.stream()
                .map(this::toApostaDoUsuarioDTO)
                .collect(Collectors.toList());
    }
}
