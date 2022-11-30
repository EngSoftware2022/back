package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Models.ConviteAmizadeDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ConviteAmizadeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ConviteAmizadeDTO toConviteAmizadeDTO(ConviteAmizade conviteAmizade) {
        return modelMapper.map(conviteAmizade, ConviteAmizadeDTO.class);
    }

    public List<ConviteAmizadeDTO> toConviteAmizadeDTOList(List<ConviteAmizade> conviteAmizadeList) {
        return conviteAmizadeList.stream()
                .map(this::toConviteAmizadeDTO)
                .collect(Collectors.toList());
    }
}
