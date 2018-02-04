package com.iblotus.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.iblotus.service.AnnounceReportTaskService;
import com.iblotus.web.rest.errors.BadRequestAlertException;
import com.iblotus.web.rest.util.HeaderUtil;
import com.iblotus.web.rest.util.PaginationUtil;
import com.iblotus.service.dto.AnnounceReportTaskDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AnnounceReportTask.
 */
@RestController
@RequestMapping("/api")
public class AnnounceReportTaskResource {

    private final Logger log = LoggerFactory.getLogger(AnnounceReportTaskResource.class);

    private static final String ENTITY_NAME = "announceReportTask";

    private final AnnounceReportTaskService announceReportTaskService;

    public AnnounceReportTaskResource(AnnounceReportTaskService announceReportTaskService) {
        this.announceReportTaskService = announceReportTaskService;
    }

    /**
     * POST  /announce-report-tasks : Create a new announceReportTask.
     *
     * @param announceReportTaskDTO the announceReportTaskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new announceReportTaskDTO, or with status 400 (Bad Request) if the announceReportTask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/announce-report-tasks")
    @Timed
    public ResponseEntity<AnnounceReportTaskDTO> createAnnounceReportTask(@RequestBody AnnounceReportTaskDTO announceReportTaskDTO) throws URISyntaxException {
        log.debug("REST request to save AnnounceReportTask : {}", announceReportTaskDTO);
        if (announceReportTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new announceReportTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnnounceReportTaskDTO result = announceReportTaskService.save(announceReportTaskDTO);
        return ResponseEntity.created(new URI("/api/announce-report-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /announce-report-tasks : Updates an existing announceReportTask.
     *
     * @param announceReportTaskDTO the announceReportTaskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated announceReportTaskDTO,
     * or with status 400 (Bad Request) if the announceReportTaskDTO is not valid,
     * or with status 500 (Internal Server Error) if the announceReportTaskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/announce-report-tasks")
    @Timed
    public ResponseEntity<AnnounceReportTaskDTO> updateAnnounceReportTask(@RequestBody AnnounceReportTaskDTO announceReportTaskDTO) throws URISyntaxException {
        log.debug("REST request to update AnnounceReportTask : {}", announceReportTaskDTO);
        if (announceReportTaskDTO.getId() == null) {
            return createAnnounceReportTask(announceReportTaskDTO);
        }
        AnnounceReportTaskDTO result = announceReportTaskService.save(announceReportTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, announceReportTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /announce-report-tasks : get all the announceReportTasks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of announceReportTasks in body
     */
    @GetMapping("/announce-report-tasks")
    @Timed
    public ResponseEntity<List<AnnounceReportTaskDTO>> getAllAnnounceReportTasks(Pageable pageable) {
        log.debug("REST request to get a page of AnnounceReportTasks");
        Page<AnnounceReportTaskDTO> page = announceReportTaskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/announce-report-tasks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /announce-report-tasks/:id : get the "id" announceReportTask.
     *
     * @param id the id of the announceReportTaskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the announceReportTaskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/announce-report-tasks/{id}")
    @Timed
    public ResponseEntity<AnnounceReportTaskDTO> getAnnounceReportTask(@PathVariable String id) {
        log.debug("REST request to get AnnounceReportTask : {}", id);
        AnnounceReportTaskDTO announceReportTaskDTO = announceReportTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(announceReportTaskDTO));
    }

    /**
     * DELETE  /announce-report-tasks/:id : delete the "id" announceReportTask.
     *
     * @param id the id of the announceReportTaskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/announce-report-tasks/{id}")
    @Timed
    public ResponseEntity<Void> deleteAnnounceReportTask(@PathVariable String id) {
        log.debug("REST request to delete AnnounceReportTask : {}", id);
        announceReportTaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
