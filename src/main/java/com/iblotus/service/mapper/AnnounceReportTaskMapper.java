package com.iblotus.service.mapper;

import com.iblotus.domain.*;
import com.iblotus.service.dto.AnnounceReportTaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AnnounceReportTask and its DTO AnnounceReportTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnnounceReportTaskMapper extends EntityMapper<AnnounceReportTaskDTO, AnnounceReportTask> {


}
