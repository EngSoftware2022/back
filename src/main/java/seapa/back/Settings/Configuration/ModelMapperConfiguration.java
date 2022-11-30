package seapa.back.Settings.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ConviteAmizade;
import seapa.back.Models.ConviteAmizadeDTO;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(ConviteAmizade.class, ConviteAmizadeDTO.class)
                .addMapping(src -> src.getSolicitante().getId(), ConviteAmizadeDTO::setSolicitanteId)
                .addMapping(src -> src.getSolicitado().getId(), ConviteAmizadeDTO::setSolicitadoId);

        return modelMapper;
    }
}
