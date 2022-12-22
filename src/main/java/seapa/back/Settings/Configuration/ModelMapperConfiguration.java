package seapa.back.Settings.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Models.DTOs.ConviteAmizadeDTO;

import java.text.SimpleDateFormat;

@Configuration
public class ModelMapperConfiguration {

    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ConviteAmizade.class, ConviteAmizadeDTO.class)
                .addMapping(src -> src.getSolicitante().getId(), ConviteAmizadeDTO::setSolicitanteId)
                .addMapping(src -> src.getSolicitante().getNomeUsuario(), ConviteAmizadeDTO::setSolicitanteNome)
                .addMapping(src -> src.getSolicitado().getId(), ConviteAmizadeDTO::setSolicitadoId)
                .addMapping(src -> src.getSolicitado().getNomeUsuario(), ConviteAmizadeDTO::setSolicitadoNome);

        return modelMapper;
    }
}
