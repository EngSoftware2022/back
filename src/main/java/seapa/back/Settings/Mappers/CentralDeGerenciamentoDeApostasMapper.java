package seapa.back.Settings.Mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seapa.back.Entitys.BetManagerEntitys.ApostasEntitys.CentralDeGerenciamentoDasApostas;
import seapa.back.Models.Requests.CentralDeGerenciamentoDeApostasRequest;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CentralDeGerenciamentoDeApostasMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CentralDeGerenciamentoDeApostasRequest toCentralDeGerenciamentoDeApostasRequest(CentralDeGerenciamentoDasApostas centralDeGerenciamentoDasApostas) {
        return modelMapper.map(centralDeGerenciamentoDasApostas, CentralDeGerenciamentoDeApostasRequest.class);
    }

    public List<CentralDeGerenciamentoDeApostasRequest> toConviteAmizadeDTOList(List<CentralDeGerenciamentoDasApostas> centralDeGerenciamentoDasApostasList) {
        return centralDeGerenciamentoDasApostasList.stream()
                .map(this::toCentralDeGerenciamentoDeApostasRequest)
                .collect(Collectors.toList());
    }
}


