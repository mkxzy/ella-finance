package com.iblotus.repository;

import com.iblotus.domain.AnnounceReportTask;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AnnounceReportTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnounceReportTaskRepository extends MongoRepository<AnnounceReportTask, String> {

}
