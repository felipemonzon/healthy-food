package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.models.requests.OfficeRequest;
import com.moontech.healthyfood.models.responses.OfficeResponse;
import com.moontech.healthyfood.services.OfficeService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
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
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
class OfficeControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de sucursales. */
  @MockBean private OfficeService officeService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de perfiles. */
  private static final String PROFILE_BASE_PATH = "/offices";

  @Test
  @DisplayName("GET /offices empty list")
  void retrieve_empty_profiles(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    Mockito.when(this.officeService.retrieve()).thenReturn(new ArrayList<>());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PROFILE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /offices  search office")
  void search_office(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    Mockito.when(this.officeService.retrieve())
        .thenReturn(Collections.singletonList(this.getOfficeResponse()));
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PROFILE_BASE_PATH + "/lejos")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /offices ")
  void retrieve_all_profiles(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    Mockito.when(this.officeService.retrieve())
        .thenReturn(Collections.singletonList(this.getOfficeResponse()));
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PROFILE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)));
  }

  @Test
  @DisplayName("POST /offices success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(PROFILE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  @DisplayName("PUT /offices success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(PROFILE_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getOfficeRequest())))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  private OfficeRequest getOfficeRequest() {
    OfficeRequest request = new OfficeRequest();
    request.setId(1L);
    request.setName("lejana");
    request.setAddress("far far away");
    request.setActive(Boolean.TRUE);
    request.setPhone("6677156788");
    request.setIdManager(1L);
    return request;
  }

  private OfficeResponse getOfficeResponse() {
    OfficeResponse response = new OfficeResponse();
    response.setId(1L);
    response.setName(StringUtils.EMPTY);
    return response;
  }
}
