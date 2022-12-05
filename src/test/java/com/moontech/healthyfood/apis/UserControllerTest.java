package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.enums.Genre;
import com.moontech.healthyfood.models.requests.UserRequest;
import com.moontech.healthyfood.services.RoleService;
import com.moontech.healthyfood.services.UserService;
import java.util.HashSet;
import java.util.Set;
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
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Prueba del controlador de usuarios.
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
class UserControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de usuarios. */
  @Autowired private UserService userService;
  /** Servicio para perfiles. */
  @Autowired private RoleService roleService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de usuarios. */
  private static final String USER_BASE_PATH = "/users";

  @Test
  @Order(1)
  @DisplayName("GET /users empty list")
  void retrieve_empty_users(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(2)
  @Sql({"classpath:insert-office-data2.sql", "classpath:insert-rol-data.sql"})
  @DisplayName("POST /users success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(this.getUserRequest("123456", "felipemonzon"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(3)
  @DisplayName("PUT /users not success")
  void update_not_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(USER_BASE_PATH + "/4")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getUserRequest("", "felipemonzon2705"))))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  @DisplayName("GET /user initial")
  void retrieve_initial_data(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USER_BASE_PATH + "/initial")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /users search success")
  void search_users(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(USER_BASE_PATH + "/felipe")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /users success")
  void retrieve_all_users(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    String response =
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get(USER_BASE_PATH)
                    .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)))
            .andReturn()
            .getResponse()
            .getContentAsString();
    log.info("usuarios encontrados {}", response);
  }

  @Test
  @DisplayName("POST /users/profile success")
  void update_user_profile(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USER_BASE_PATH + "/profile")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .with(
                    SecurityMockMvcRequestPostProcessors.user(
                        TestConstants.TEST_USER("felipemonzon")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getUserRequest("123", "felipemonzon"))))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @DisplayName("POST /users/profile not equals username")
  void update_user_profile_not_equals_username(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USER_BASE_PATH + "/profile")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .with(
                    SecurityMockMvcRequestPostProcessors.user(TestConstants.TEST_USER("username")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(this.getUserRequest("123", "usernmae"))))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Order(5)
  @DisplayName("POST /users exists")
  void save_NoData(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(USER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getUserRequest("", "felipemonzon"))))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @Order(6)
  @DisplayName("PUT /users success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(USER_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.getUserRequest("", "felipemonzon"))))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  private UserRequest getUserRequest(String password, String username) {
    UserRequest request = new UserRequest();
    request.setId(1L);
    request.setCel("6671568899");
    request.setGenre(Genre.MALE);
    request.setEmail("felipemonzon@gmail.com");
    request.setFirstName("Felipe");
    request.setLastName("monzon");
    request.setUsername(username);
    request.setBranchOfficeId(3L);
    request.setPassword(password);
    Set<Long> profiles = new HashSet<>();
    profiles.add(4L);
    request.setProfiles(profiles);
    return request;
  }
}
