package com.iblotus.service;

import com.iblotus.domain.Foo;
import com.iblotus.repository.FooRepository;
import com.iblotus.service.dto.FooDTO;
import com.iblotus.service.mapper.FooMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Foo.
 */
@Service
public class FooService {

    private final Logger log = LoggerFactory.getLogger(FooService.class);

    private final FooRepository fooRepository;

    private final FooMapper fooMapper;

    public FooService(FooRepository fooRepository, FooMapper fooMapper) {
        this.fooRepository = fooRepository;
        this.fooMapper = fooMapper;
    }

    /**
     * Save a foo.
     *
     * @param fooDTO the entity to save
     * @return the persisted entity
     */
    public FooDTO save(FooDTO fooDTO) {
        log.debug("Request to save Foo : {}", fooDTO);
        Foo foo = fooMapper.toEntity(fooDTO);
        foo = fooRepository.save(foo);
        return fooMapper.toDto(foo);
    }

    /**
     * Get all the foos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<FooDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Foos");
        return fooRepository.findAll(pageable)
            .map(fooMapper::toDto);
    }

    /**
     * Get one foo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public FooDTO findOne(String id) {
        log.debug("Request to get Foo : {}", id);
        Foo foo = fooRepository.findOne(id);
        return fooMapper.toDto(foo);
    }

    /**
     * Delete the foo by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Foo : {}", id);
        fooRepository.delete(id);
    }
}
