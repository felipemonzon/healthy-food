package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.AppointmentRequest;
import com.moontech.healthyfood.models.requests.CustomerRequest;
import com.moontech.healthyfood.models.responses.AppointmentResponse;
import com.moontech.healthyfood.models.responses.CustomerResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.AppointmentService;
import com.moontech.healthyfood.services.CustomerService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * APIS para clientes.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 26 abr., 2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.CUSTOMER_BASE_PATH)
public class CustomerController {
  /** Servicio de citas. */
  private final AppointmentService appointmentService;
  /** Servicio para clientes. */
  private final CustomerService customerService;

  /**
   * Consulta las citas de los clientes.
   *
   * @param customerId identificador del cliente
   * @param request datos de la cita.
   * @return citas asignadas al cliente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PostMapping(
      path = RoutesConstant.CUSTOMER_APPOINTMENTS_PATH,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AppointmentResponse>> findByCustomerAndStatus(
      @PathVariable(name = "idCustomer") @Valid @NotNull Long customerId,
      @RequestBody @Valid AppointmentRequest request) {
    return ResponseEntity.ok(
        this.appointmentService.findByCustomerAndStatus(customerId, request.getStatus()));
  }

  /**
   * Guarda los datos de un cliente
   *
   * @param request datos del cliente
   * @return 201 si se guardo con éxito
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> save(@RequestBody @Valid CustomerRequest request) {
    this.customerService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Consulta todos los clientes.
   *
   * @return lista de clientes encontrados
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CustomerResponse>> retrieve() {
    return ResponseEntity.ok(this.customerService.retrieve());
  }
}
