package com.iblotus.web.rest;

import com.iblotus.EllaFinanceApp;

import com.iblotus.domain.AnnounceReportTask;
import com.iblotus.repository.AnnounceReportTaskRepository;
import com.iblotus.service.AnnounceReportTaskService;
import com.iblotus.service.dto.AnnounceReportTaskDTO;
import com.iblotus.service.mapper.AnnounceReportTaskMapper;
import com.iblotus.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;

import java.util.List;

import static com.iblotus.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AnnounceReportTaskResource REST controller.
 *
 * @see AnnounceReportTaskResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EllaFinanceApp.class)
public class AnnounceReportTaskResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final byte[] DEFAULT_COMPANY_LIST = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COMPANY_LIST = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_COMPANY_LIST_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COMPANY_LIST_CONTENT_TYPE = "image/png";

    @Autowired
    private AnnounceReportTaskRepository announceReportTaskRepository;

    @Autowired
    private AnnounceReportTaskMapper announceReportTaskMapper;

    @Autowired
    private AnnounceReportTaskService announceReportTaskService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAnnounceReportTaskMockMvc;

    private AnnounceReportTask announceReportTask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnnounceReportTaskResource announceReportTaskResource = new AnnounceReportTaskResource(announceReportTaskService);
        this.restAnnounceReportTaskMockMvc = MockMvcBuilders.standaloneSetup(announceReportTaskResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnnounceReportTask createEntity() {
        AnnounceReportTask announceReportTask = new AnnounceReportTask()
            .title(DEFAULT_TITLE)
            .year(DEFAULT_YEAR)
            .companyList(DEFAULT_COMPANY_LIST)
            .companyListContentType(DEFAULT_COMPANY_LIST_CONTENT_TYPE);
        return announceReportTask;
    }

    @Before
    public void initTest() {
        announceReportTaskRepository.deleteAll();
        announceReportTask = createEntity();
    }

    @Test
    public void createAnnounceReportTask() throws Exception {
        int databaseSizeBeforeCreate = announceReportTaskRepository.findAll().size();

        // Create the AnnounceReportTask
        AnnounceReportTaskDTO announceReportTaskDTO = announceReportTaskMapper.toDto(announceReportTask);
        restAnnounceReportTaskMockMvc.perform(post("/api/announce-report-tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(announceReportTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the AnnounceReportTask in the database
        List<AnnounceReportTask> announceReportTaskList = announceReportTaskRepository.findAll();
        assertThat(announceReportTaskList).hasSize(databaseSizeBeforeCreate + 1);
        AnnounceReportTask testAnnounceReportTask = announceReportTaskList.get(announceReportTaskList.size() - 1);
        assertThat(testAnnounceReportTask.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAnnounceReportTask.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testAnnounceReportTask.getCompanyList()).isEqualTo(DEFAULT_COMPANY_LIST);
        assertThat(testAnnounceReportTask.getCompanyListContentType()).isEqualTo(DEFAULT_COMPANY_LIST_CONTENT_TYPE);
    }

    @Test
    public void createAnnounceReportTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = announceReportTaskRepository.findAll().size();

        // Create the AnnounceReportTask with an existing ID
        announceReportTask.setId("existing_id");
        AnnounceReportTaskDTO announceReportTaskDTO = announceReportTaskMapper.toDto(announceReportTask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnnounceReportTaskMockMvc.perform(post("/api/announce-report-tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(announceReportTaskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnnounceReportTask in the database
        List<AnnounceReportTask> announceReportTaskList = announceReportTaskRepository.findAll();
        assertThat(announceReportTaskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllAnnounceReportTasks() throws Exception {
        // Initialize the database
        announceReportTaskRepository.save(announceReportTask);

        // Get all the announceReportTaskList
        restAnnounceReportTaskMockMvc.perform(get("/api/announce-report-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(announceReportTask.getId())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].companyListContentType").value(hasItem(DEFAULT_COMPANY_LIST_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].companyList").value(hasItem(Base64Utils.encodeToString(DEFAULT_COMPANY_LIST))));
    }

    @Test
    public void getAnnounceReportTask() throws Exception {
        // Initialize the database
        announceReportTaskRepository.save(announceReportTask);

        // Get the announceReportTask
        restAnnounceReportTaskMockMvc.perform(get("/api/announce-report-tasks/{id}", announceReportTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(announceReportTask.getId()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.companyListContentType").value(DEFAULT_COMPANY_LIST_CONTENT_TYPE))
            .andExpect(jsonPath("$.companyList").value(Base64Utils.encodeToString(DEFAULT_COMPANY_LIST)));
    }

    @Test
    public void getNonExistingAnnounceReportTask() throws Exception {
        // Get the announceReportTask
        restAnnounceReportTaskMockMvc.perform(get("/api/announce-report-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAnnounceReportTask() throws Exception {
        // Initialize the database
        announceReportTaskRepository.save(announceReportTask);
        int databaseSizeBeforeUpdate = announceReportTaskRepository.findAll().size();

        // Update the announceReportTask
        AnnounceReportTask updatedAnnounceReportTask = announceReportTaskRepository.findOne(announceReportTask.getId());
        updatedAnnounceReportTask
            .title(UPDATED_TITLE)
            .year(UPDATED_YEAR)
            .companyList(UPDATED_COMPANY_LIST)
            .companyListContentType(UPDATED_COMPANY_LIST_CONTENT_TYPE);
        AnnounceReportTaskDTO announceReportTaskDTO = announceReportTaskMapper.toDto(updatedAnnounceReportTask);

        restAnnounceReportTaskMockMvc.perform(put("/api/announce-report-tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(announceReportTaskDTO)))
            .andExpect(status().isOk());

        // Validate the AnnounceReportTask in the database
        List<AnnounceReportTask> announceReportTaskList = announceReportTaskRepository.findAll();
        assertThat(announceReportTaskList).hasSize(databaseSizeBeforeUpdate);
        AnnounceReportTask testAnnounceReportTask = announceReportTaskList.get(announceReportTaskList.size() - 1);
        assertThat(testAnnounceReportTask.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAnnounceReportTask.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testAnnounceReportTask.getCompanyList()).isEqualTo(UPDATED_COMPANY_LIST);
        assertThat(testAnnounceReportTask.getCompanyListContentType()).isEqualTo(UPDATED_COMPANY_LIST_CONTENT_TYPE);
    }

    @Test
    public void updateNonExistingAnnounceReportTask() throws Exception {
        int databaseSizeBeforeUpdate = announceReportTaskRepository.findAll().size();

        // Create the AnnounceReportTask
        AnnounceReportTaskDTO announceReportTaskDTO = announceReportTaskMapper.toDto(announceReportTask);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAnnounceReportTaskMockMvc.perform(put("/api/announce-report-tasks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(announceReportTaskDTO)))
            .andExpect(status().isCreated());

        // Validate the AnnounceReportTask in the database
        List<AnnounceReportTask> announceReportTaskList = announceReportTaskRepository.findAll();
        assertThat(announceReportTaskList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAnnounceReportTask() throws Exception {
        // Initialize the database
        announceReportTaskRepository.save(announceReportTask);
        int databaseSizeBeforeDelete = announceReportTaskRepository.findAll().size();

        // Get the announceReportTask
        restAnnounceReportTaskMockMvc.perform(delete("/api/announce-report-tasks/{id}", announceReportTask.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AnnounceReportTask> announceReportTaskList = announceReportTaskRepository.findAll();
        assertThat(announceReportTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnounceReportTask.class);
        AnnounceReportTask announceReportTask1 = new AnnounceReportTask();
        announceReportTask1.setId("id1");
        AnnounceReportTask announceReportTask2 = new AnnounceReportTask();
        announceReportTask2.setId(announceReportTask1.getId());
        assertThat(announceReportTask1).isEqualTo(announceReportTask2);
        announceReportTask2.setId("id2");
        assertThat(announceReportTask1).isNotEqualTo(announceReportTask2);
        announceReportTask1.setId(null);
        assertThat(announceReportTask1).isNotEqualTo(announceReportTask2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnounceReportTaskDTO.class);
        AnnounceReportTaskDTO announceReportTaskDTO1 = new AnnounceReportTaskDTO();
        announceReportTaskDTO1.setId("id1");
        AnnounceReportTaskDTO announceReportTaskDTO2 = new AnnounceReportTaskDTO();
        assertThat(announceReportTaskDTO1).isNotEqualTo(announceReportTaskDTO2);
        announceReportTaskDTO2.setId(announceReportTaskDTO1.getId());
        assertThat(announceReportTaskDTO1).isEqualTo(announceReportTaskDTO2);
        announceReportTaskDTO2.setId("id2");
        assertThat(announceReportTaskDTO1).isNotEqualTo(announceReportTaskDTO2);
        announceReportTaskDTO1.setId(null);
        assertThat(announceReportTaskDTO1).isNotEqualTo(announceReportTaskDTO2);
    }
}
