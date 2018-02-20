package com.tide.assembler.impl;

import com.tide.assembler.Assembler;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAssembler<DTO, ENTITY> implements Assembler<DTO, ENTITY> {

    @Override
    public List<DTO> toDtos(List<ENTITY> entities) {
        final List<DTO> result = new ArrayList<>();
        for (ENTITY entity : entities) {
            result.add(toDto(entity));
        }
        return result;
    }

    @Override
    public List<ENTITY> toEntities(List<DTO> dtos) {
        final List<ENTITY> result = new ArrayList<>();
        for (DTO dto : dtos) {
            result.add(toEntity(dto));
        }
        return result;
    }
}
