package com.tide.assembler;

import java.util.List;

public interface Assembler<DTO, ENTITY> {

    DTO toDto(ENTITY entity);
    List<DTO> toDtos(List<ENTITY> entities);

    ENTITY toEntity(DTO dto);
    List<ENTITY> toEntities(List<DTO> dtos);
}
