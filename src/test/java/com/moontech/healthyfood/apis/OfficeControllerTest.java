package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.models.requests.OfficeRequest;
import com.moontech.healthyfood.services.OfficeService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Prueba del controlador de sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 21 nov., 2022
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OfficeControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de sucursales. */
  @Autowired private OfficeService officeService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de perfiles. */
  private static final String OFFICE_BASE_PATH = "/offices";

  @Test
  @Order(1)
  @DisplayName("GET /offices empty list")
  void retrieve_empty(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(OFFICE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(2)
  @DisplayName("POST /offices violation integrity")
  void save_violation_integrity(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(OFFICE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(3)
  @DisplayName("PUT /offices not success")
  void update_not_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(OFFICE_BASE_PATH + "/2")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(4)
  @Sql({"classpath:insert-user-data.sql"})
  @DisplayName("POST /offices success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(OFFICE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(5)
  @DisplayName("POST /offices exists")
  void save_NoData(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(OFFICE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(6)
  @DisplayName("PUT /offices success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(OFFICE_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @Order(7)
  @DisplayName("GET /offices search success")
  void search_office(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(OFFICE_BASE_PATH + "/lejos")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(8)
  @DisplayName("GET /offices success")
  void retrieve_all(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    String offices =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get(OFFICE_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)))
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("Sucursales encontradas en test {}", offices);
  }

  private OfficeRequest getOfficeRequest() {
    OfficeRequest request = new OfficeRequest();
    request.setId(1L);
    request.setName("lejana");
    request.setAddress("far far away");
    request.setActive(Boolean.TRUE);
    request.setPhone("6677156788");
    request.setIdManager(2L);
    return request;
  }
}
