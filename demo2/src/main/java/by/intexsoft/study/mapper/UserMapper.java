package by.intexsoft.study.mapper;

import by.intexsoft.study.model.User;
import by.intexsoft.study.model.UserDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(source = "roles", target = "rolesDtoList")
    UserDto toDto(User user);

    @InheritInverseConfiguration
    User fromDto(UserDto userDto);

    List<UserDto> toDtos(List<User> users);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

}
