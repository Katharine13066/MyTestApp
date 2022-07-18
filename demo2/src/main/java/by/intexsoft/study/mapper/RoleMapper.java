package by.intexsoft.study.mapper;

import by.intexsoft.study.model.Role;
import by.intexsoft.study.model.RoleDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

   RoleDto toDto(Role role);

   @InheritInverseConfiguration
   Role fromDto(RoleDto roleDto);

   List<RoleDto> toDtos(List<Role> roles);

   @InheritConfiguration
   @Mapping(target = "id", ignore = true)
   void updateRoleFromDto(RoleDto roleDto, @MappingTarget Role role);

}