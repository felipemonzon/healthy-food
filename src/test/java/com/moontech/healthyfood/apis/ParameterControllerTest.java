package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.models.requests.ParameterRequest;
import com.moontech.healthyfood.services.ParameterService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Prueba del controlador de parámetros.
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
class ParameterControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de parámetros. */
  @Autowired private ParameterService parameterService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de parámetros. */
  private static final String PARAMETER_BASE_PATH = "/parameters";

  @Test
  @Order(1)
  @DisplayName("GET /parameters empty list")
  void retrieve_empty_profiles(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PARAMETER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /parameters search office")
  void search_office(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PARAMETER_BASE_PATH + "/LCAL")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /parameters success")
  void retrieve_all_profiles(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());

    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PARAMETER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)))
        .andReturn();
  }

  @Test
  @Order(2)
  @DisplayName("POST /parameters success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(PARAMETER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getParameterRequest("LCAL"))))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @Order(4)
  @DisplayName("POST /parameters bad request")
  void save_badRequest(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(PARAMETER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getParameterRequest(""))))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  @Order(3)
  @DisplayName("PUT /parameters success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(PARAMETER_BASE_PATH + "/LCAL")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getParameterRequest("LCAL"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(6)
  @DisplayName("DELETE /parameters success")
  void delete_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.delete(PARAMETER_BASE_PATH + "/LCAL")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(5)
  @DisplayName("DELETE /parameters notFound")
  void delete_notFound(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.delete(PARAMETER_BASE_PATH + "/LCA")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  private ParameterRequest getParameterRequest(String id) {
    ParameterRequest request = new ParameterRequest();
    request.setId(id);
    request.setValue("LCAL");
    request.setDescription("Calorias");
    request.setStatus(Boolean.TRUE);
    return request;
  }
}
