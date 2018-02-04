package com.iblotus.service;

import com.iblotus.domain.AnnounceReportTask;
import com.iblotus.repository.AnnounceReportTaskRepository;
import com.iblotus.service.dto.AnnounceReportTaskDTO;
import com.iblotus.service.mapper.AnnounceReportTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing AnnounceReportTask.
 */
@Service
public class AnnounceReportTaskService {

    private final Logger log = LoggerFactory.getLogger(AnnounceReportTaskService.class);

    private final AnnounceReportTaskRepository announceReportTaskRepository;

    private final AnnounceReportTaskMapper announceReportTaskMapper;

    public AnnounceReportTaskService(AnnounceReportTaskRepository announceReportTaskRepository, AnnounceReportTaskMapper announceReportTaskMapper) {
        this.announceReportTaskRepository = announceReportTaskRepository;
        this.announceReportTaskMapper = announceReportTaskMapper;
    }

    /**
     * Save a announceReportTask.
     *
     * @param announceReportTaskDTO the entity to save
     * @return the persisted entity
     */
    public AnnounceReportTaskDTO save(AnnounceReportTaskDTO announceReportTaskDTO) {
        log.debug("Request to save AnnounceReportTask : {}", announceReportTaskDTO);
        AnnounceReportTask announceReportTask = announceReportTaskMapper.toEntity(announceReportTaskDTO);
        announceReportTask = announceReportTaskRepository.save(announceReportTask);
        return announceReportTaskMapper.toDto(announceReportTask);
    }

    /**
     * Get all the announceReportTasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<AnnounceReportTaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnnounceReportTasks");
        return announceReportTaskRepository.findAll(pageable)
            .map(announceReportTaskMapper::toDto);
    }

    /**
     * Get one announceReportTask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public AnnounceReportTaskDTO findOne(String id) {
        log.debug("Request to get AnnounceReportTask : {}", id);
        AnnounceReportTask announceReportTask = announceReportTaskRepository.findOne(id);
        return announceReportTaskMapper.toDto(announceReportTask);
    }

    /**
     * Delete the announceReportTask by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete AnnounceReportTask : {}", id);
        announceReportTaskRepository.delete(id);
    }
}
