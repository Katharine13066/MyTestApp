package by.intexsoft.study.mapper;

import by.intexsoft.study.model.Author;
import by.intexsoft.study.model.AuthorDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    @InheritInverseConfiguration
    Author fromDto(AuthorDto authorDto);

    List<AuthorDto> toDtos(List<Author> authors);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateAuthorFromDto(AuthorDto authorDto, @MappingTarget Author author);

}
