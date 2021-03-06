package by.intexsoft.study.mapper;

import by.intexsoft.study.model.AuthenticationRequest;
import by.intexsoft.study.model.AuthenticationRequestDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthenticationRequestMapper {

    AuthenticationRequestDto toDto(AuthenticationRequest authenticationRequest);

    @InheritInverseConfiguration
    AuthenticationRequest fromDto(AuthenticationRequestDto authenticationRequestDto);

    List<AuthenticationRequestDto> toDtos(List<AuthenticationRequest> authenticationRequests);

}