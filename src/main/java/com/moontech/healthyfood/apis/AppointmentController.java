package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.responses.AppointmentResponse;
import com.moontech.healthyfood.models.responses.CustomerResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.AppointmentService;
import com.moontech.healthyfood.services.CustomerService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APIS para citas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 24 nov., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.APPOINTMENT_BASE_PATH)
public class AppointmentController {
  /** Servicio de citas. */
  private final AppointmentService appointmentService;
  /** Servicio de clientes. */
  private final CustomerService customerService;

  /**
   * Carga los datos iniciales para la pantalla de citas.
   *
   * @return lista de clientes
   */
  @GetMapping(path = RoutesConstant.INITIAL_DATA_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CustomerResponse>> initialAppointment() {
    return ResponseEntity.ok(this.customerService.retrieve());
  }

  /**
   * Consulta todas las citas.
   *
   * @return lista de citas
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AppointmentResponse>> retrieve() {
    AppointmentResponse response = new AppointmentResponse();
    response.setId(1L);
    response.setTitle("Cita Felipe");
    response.setStart(LocalDateTime.now());
    response.setEnd(LocalDateTime.now().plusMinutes(30L));

    AppointmentResponse response2 = new AppointmentResponse();
    response2.setId(1L);
    response2.setTitle("Cita Felipe");
    response2.setStart(LocalDateTime.now().minusDays(1));
    response2.setEnd(LocalDateTime.now().minusDays(1).plusHours(1L));

    return ResponseEntity.ok(this.appointmentService.retrieve());
  }
}
