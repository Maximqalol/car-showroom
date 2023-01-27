package ru.example.carshowroom.data.mapper;

import org.mapstruct.Mapper;
import ru.example.carshowroom.data.dto.ProducerDto;
import ru.example.carshowroom.data.entity.Producer;

@Mapper(componentModel = "spring")
public interface ProducerMapper {

    ProducerDto toDto(Producer producer);

    Producer fromDto(ProducerDto producerDto);

}
