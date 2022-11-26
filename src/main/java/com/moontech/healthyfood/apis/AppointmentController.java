package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.responses.AppointmentResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import java.time.LocalDateTime;
import java.util.Arrays;
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

    return ResponseEntity.ok(Arrays.asList(response, response2));
  }
}
