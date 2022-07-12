package by.intexsoft.study.mapper;

import by.intexsoft.study.model.AuthenticationResponse;
import by.intexsoft.study.model.AuthenticationResponseDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthenticationResponseMapper {

    AuthenticationResponseMapper authenticationResponseMapper = Mappers.getMapper(AuthenticationResponseMapper.class);

    AuthenticationResponseDto toDto(AuthenticationResponse authenticationResponse);

    @InheritInverseConfiguration
    AuthenticationResponse fromDto(AuthenticationResponseDto authenticationResponseDto);

    List<AuthenticationResponseDto> toDtos(List<AuthenticationResponse> authenticationResponses);
}
