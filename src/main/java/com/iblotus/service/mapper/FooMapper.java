package com.iblotus.service.mapper;

import com.iblotus.domain.*;
import com.iblotus.service.dto.FooDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Foo and its DTO FooDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FooMapper extends EntityMapper<FooDTO, Foo> {


}
