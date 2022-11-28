package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.models.requests.UnitRequest;
import com.moontech.healthyfood.services.UnitService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Pruebas para unidades de medida.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 27 nov., 2022
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UnitsControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de unidades de medida. */
  @Autowired private UnitService unitService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de usuarios. */
  private static final String UNITS_BASE_PATH = "/units";

  @Test
  @Order(1)
  @DisplayName("POST /units success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(UNITS_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getUnitRequest())))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(2)
  @DisplayName("POST /units exists")
  void save_exists_unit(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(UNITS_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getUnitRequest())))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(3)
  @DisplayName("GET /units success")
  void unis_retrieve_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(UNITS_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(4)
  @DisplayName("GET /units search success")
  void search_units_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(UNITS_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(5)
  @DisplayName("PUT /units success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(UNITS_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getUnitRequest())))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @Order(6)
  @DisplayName("PUT /units not exists")
  void update_units_not_exists(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(UNITS_BASE_PATH + "/2")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getUnitRequest())))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(7)
  @DisplayName("DELETE /units success")
  void delete_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.delete(UNITS_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  private UnitRequest getUnitRequest() {
    UnitRequest request = new UnitRequest();
    request.setId(1L);
    request.setName("ABS");
    request.setAbbreviation("ABS");
    request.setStatus(Boolean.TRUE);
    return request;
  }
}
