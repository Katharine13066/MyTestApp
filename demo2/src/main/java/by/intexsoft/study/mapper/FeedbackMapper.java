package by.intexsoft.study.mapper;

import by.intexsoft.study.model.Feedback;
import by.intexsoft.study.model.FeedbackDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = BookMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FeedbackMapper {

    FeedbackDto toDto(Feedback feedback);

    @InheritInverseConfiguration
    Feedback fromDto(FeedbackDto feedbackDto);

    List<FeedbackDto> toDtos(List<Feedback> feedbacks);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    void updateFeedbackFromDto(FeedbackDto feedbackDto, @MappingTarget Feedback feedback);

}
