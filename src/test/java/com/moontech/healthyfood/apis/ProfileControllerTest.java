package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.services.RoleService;
import lombok.extern.slf4j.Slf4j;
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

import java.util.ArrayList;
import java.util.UUID;

/**
 * Test del controlador de perfiles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 21 nov., 2022
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = "ADMIN")
class ProfileControllerTest extends MysqlBaseConfigurationTest {
  @Autowired private MockMvc mockMvc;
  /** Servicio de perfiles. */
  @MockBean private RoleService roleService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de perfiles. */
  private static final String PROFILE_BASE_PATH = "/profiles";

  @Test
  @DisplayName("GET /profiles empty list")
  void retrieve_all_profiles(TestInfo testInfo) throws Exception {
    log.info("Running {}", testInfo.getDisplayName());
    Mockito.when(this.roleService.retrieve()).thenReturn(new ArrayList<>());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(PROFILE_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }
}
