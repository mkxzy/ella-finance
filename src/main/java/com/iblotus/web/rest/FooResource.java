package com.iblotus.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.iblotus.service.FooService;
import com.iblotus.web.rest.errors.BadRequestAlertException;
import com.iblotus.web.rest.util.HeaderUtil;
import com.iblotus.web.rest.util.PaginationUtil;
import com.iblotus.service.dto.FooDTO;
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
 * REST controller for managing Foo.
 */
@RestController
@RequestMapping("/api")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    private static final String ENTITY_NAME = "foo";

    private final FooService fooService;

    public FooResource(FooService fooService) {
        this.fooService = fooService;
    }

    /**
     * POST  /foos : Create a new foo.
     *
     * @param fooDTO the fooDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fooDTO, or with status 400 (Bad Request) if the foo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foos")
    @Timed
    public ResponseEntity<FooDTO> createFoo(@RequestBody FooDTO fooDTO) throws URISyntaxException {
        log.debug("REST request to save Foo : {}", fooDTO);
        if (fooDTO.getId() != null) {
            throw new BadRequestAlertException("A new foo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FooDTO result = fooService.save(fooDTO);
        return ResponseEntity.created(new URI("/api/foos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /foos : Updates an existing foo.
     *
     * @param fooDTO the fooDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fooDTO,
     * or with status 400 (Bad Request) if the fooDTO is not valid,
     * or with status 500 (Internal Server Error) if the fooDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foos")
    @Timed
    public ResponseEntity<FooDTO> updateFoo(@RequestBody FooDTO fooDTO) throws URISyntaxException {
        log.debug("REST request to update Foo : {}", fooDTO);
        if (fooDTO.getId() == null) {
            return createFoo(fooDTO);
        }
        FooDTO result = fooService.save(fooDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fooDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foos : get all the foos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of foos in body
     */
    @GetMapping("/foos")
    @Timed
    public ResponseEntity<List<FooDTO>> getAllFoos(Pageable pageable) {
        log.debug("REST request to get a page of Foos");
        Page<FooDTO> page = fooService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/foos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /foos/:id : get the "id" foo.
     *
     * @param id the id of the fooDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fooDTO, or with status 404 (Not Found)
     */
    @GetMapping("/foos/{id}")
    @Timed
    public ResponseEntity<FooDTO> getFoo(@PathVariable String id) {
        log.debug("REST request to get Foo : {}", id);
        FooDTO fooDTO = fooService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(fooDTO));
    }

    /**
     * DELETE  /foos/:id : delete the "id" foo.
     *
     * @param id the id of the fooDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFoo(@PathVariable String id) {
        log.debug("REST request to delete Foo : {}", id);
        fooService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
