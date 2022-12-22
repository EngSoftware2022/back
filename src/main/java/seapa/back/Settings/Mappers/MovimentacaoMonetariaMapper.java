package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.CarteiraEntitys.MovimentacaoMonetaria;
import seapa.back.Models.DTOs.MovimentacaoMonetariaDTO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MovimentacaoMonetariaMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MovimentacaoMonetariaDTO toMovimentacaoMonetariaDTO(MovimentacaoMonetaria movimentacaoMonetaria) {
        return modelMapper.map(movimentacaoMonetaria, MovimentacaoMonetariaDTO.class);
    }

    public List<MovimentacaoMonetariaDTO> toMovimentacaoMonetariaDTOList(List<MovimentacaoMonetaria> movimentacaoMonetariaList) {
        return movimentacaoMonetariaList.stream()
                .map(this::toMovimentacaoMonetariaDTO)
                .collect(Collectors.toList());
    }
}
