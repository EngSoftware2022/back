package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.Banca;
import seapa.back.Models.DTOs.BancaUsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BancaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BancaUsuarioDTO toBancaUsuarioDTO(Banca bancaUsuario) {
        return modelMapper.map(bancaUsuario, BancaUsuarioDTO.class);
    }

    public List<BancaUsuarioDTO> toBancaUsuarioDTOList(List<Banca> bancaUsuarioList) {
        return bancaUsuarioList.stream()
                .map(this::toBancaUsuarioDTO)
                .collect(Collectors.toList());
    }
}
