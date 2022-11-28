package com.moontech.healthyfood.apis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moontech.healthyfood.configuration.MysqlBaseConfigurationTest;
import com.moontech.healthyfood.configuration.TestConstants;
import com.moontech.healthyfood.models.requests.SupplierRequest;
import com.moontech.healthyfood.services.SupplierService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
 * Prueba del controlador de proveedores.
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
class SupplierControllerTest extends MysqlBaseConfigurationTest {
  /** Implementación de mock mvc. */
  @Autowired private MockMvc mockMvc;
  /** Servicio de proveedores. */
  @Autowired private SupplierService supplierService;
  /** Mapper. */
  @Autowired private ObjectMapper objectMapper;
  /** Ruta base de usuarios. */
  private static final String SUPPLIER_BASE_PATH = "/suppliers";

  @Test
  @Order(1)
  @DisplayName("GET /suppliers success")
  void retrieve_suppliers_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(SUPPLIER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @DisplayName("GET /suppliers search success")
  void search_suppliers(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(SUPPLIER_BASE_PATH + "/felipe")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
  }

  @Test
  @Order(3)
  @DisplayName("POST /suppliers success")
  void save_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(SUPPLIER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(
                        this.getSupplierRequest("Proveedor del norte"))))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
  }

  @Test
  @Order(4)
  @DisplayName("POST /suppliers bad request")
  void save_badRequest(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(SUPPLIER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getSupplierRequest(""))))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(5)
  @WithMockUser(roles = TestConstants.ROLE_OTHER)
  @DisplayName("POST /suppliers unknown error")
  void save_forbidden(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(SUPPLIER_BASE_PATH)
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(this.getSupplierRequest("Proveedor"))))
        .andExpect(MockMvcResultMatchers.status().is5xxServerError());
  }

  @Test
  @Order(6)
  @DisplayName("PUT /suppliers success")
  void update_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(SUPPLIER_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(this.getSupplierRequest("Proveedor"))))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @Order(7)
  @DisplayName("PUT /suppliers not found")
  void update_notFound(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.put(SUPPLIER_BASE_PATH + "/2")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    this.objectMapper.writeValueAsString(this.getSupplierRequest("Proveedor"))))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  @Test
  @Order(7)
  @DisplayName("DELETE /suppliers success")
  void delete_success(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.delete(SUPPLIER_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  @Test
  @Order(8)
  @DisplayName("PATCH /suppliers method not supported")
  void method_not_supported(TestInfo testInfo) throws Exception {
    log.info(TestConstants.TEST_RUNNING, testInfo.getDisplayName());
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.patch(SUPPLIER_BASE_PATH + "/1")
                .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID())))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }

  private SupplierRequest getSupplierRequest(String name) {
    SupplierRequest request = new SupplierRequest();
    request.setId(1L);
    request.setName(name);
    request.setEnterprise("Surtido variado");
    request.setRfc("MOME900527MSLNLO3");
    request.setAddress("Reforma 489");
    request.setPhone("6677154399");
    request.setStatus(Boolean.TRUE);
    request.setComments(StringUtils.EMPTY);
    return request;
  }
}
