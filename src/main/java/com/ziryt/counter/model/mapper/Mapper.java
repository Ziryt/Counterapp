package com.ziryt.counter.model.mapper;

import com.ziryt.counter.model.DTO.CounterDTO;
import com.ziryt.counter.model.entity.Counter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static CounterDTO entityToDTO(Counter counter){
        return new CounterDTO(
                counter.getId(),
                counter.getName(),
                counter.getInitialValue(),
                counter.getCurrentValue(),
                counter.getTopLimit(),
                counter.getBottomLimit(),
                counter.getColor());
    }

    public static List<CounterDTO> listEntityToDTO(List<Counter> counters){
        List<CounterDTO> counterDTOS = new ArrayList<>();
        for (Counter counter:counters){
            counterDTOS.add(Mapper.entityToDTO(counter));
        }
        return counterDTOS;
    }

    public static Page<CounterDTO> pageEntityToDTO(Page<Counter> counters){
        return counters.map(Mapper::entityToDTO);
    }

    public static Counter dtoToEntity(CounterDTO counterDTO){
        return Counter.builder()
                .Id(counterDTO.Id())
                .name(counterDTO.name())
                .initialValue(counterDTO.initialValue())
                .currentValue(counterDTO.currentValue())
                .topLimit(counterDTO.topLimit())
                .bottomLimit(counterDTO.bottomLimit())
                .color(counterDTO.color())
                .build();
    }

}
