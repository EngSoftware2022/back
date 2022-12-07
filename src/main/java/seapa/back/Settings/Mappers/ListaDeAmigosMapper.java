package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ListaAmigos;
import seapa.back.Models.Responses.ListaAmigosResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ListaDeAmigosMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ListaAmigosResponse toListaDeAmigosResponse(ListaAmigos listaAmigos) {
        return modelMapper.map(listaAmigos, ListaAmigosResponse.class);
    }

    public List<ListaAmigosResponse> toListaDeAmigosResponseList(List<ListaAmigos> listaAmigosResponseList) {
        return listaAmigosResponseList.stream()
                .map(this::toListaDeAmigosResponse)
                .collect(Collectors.toList());
    }
}
