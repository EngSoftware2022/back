package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.Aposta;
import seapa.back.Models.DTOs.ApostaDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ApostasMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ApostaDTO toApostaDTO(Aposta aposta) {
        return modelMapper.map(aposta, ApostaDTO.class);
    }

    public List<ApostaDTO> toApostaDTOList(List<Aposta> apostaList) {
        return apostaList.stream()
                .map(this::toApostaDTO)
                .collect(Collectors.toList());
    }
}