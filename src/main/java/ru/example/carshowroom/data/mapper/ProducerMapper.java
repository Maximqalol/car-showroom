package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import ru.example.carshowroom.data.dto.ProducerDto;
import ru.example.carshowroom.data.entity.Producer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerDto toDto(Producer producer);

    Producer fromDto(ProducerDto producerDto);

    List<ProducerDto> toListDto(List<Producer> producers);
}
