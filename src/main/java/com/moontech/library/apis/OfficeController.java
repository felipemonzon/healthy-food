package com.moontech.library.apis;

import com.moontech.library.constants.RoutesConstant;
import com.moontech.library.models.requests.OfficeRequest;
import com.moontech.library.models.responses.OfficeResponse;
import com.moontech.library.security.constants.SecurityConstants;
import com.moontech.library.services.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * APIS de sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.OFFICE_BASE_PATH)
public class OfficeController {
  /** Servicio de sucursales. */
  private final OfficeService officeService;

  /**
   * Consulta las sucursales.
   *
   * @return lista de sucursales
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<OfficeResponse>> retrieve() {
    return ResponseEntity.ok(this.officeService.retrieve());
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> create(@RequestBody @Valid OfficeRequest request) {
    this.officeService.create(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(
      path = RoutesConstant.DATA_MODIFIED_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> update(
      @PathVariable @NotNull @Min(value = 1) Long id, @RequestBody @Valid OfficeRequest request) {
    this.officeService.update(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Consulta los datos de la sucursal por un dato
   *
   * @return sucursales encontradas
   */
  @GetMapping(path = RoutesConstant.SEARCH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<OfficeResponse>> findBy(@PathVariable @Valid @NotBlank String search) {
    return ResponseEntity.ok(this.officeService.findBy(search.trim().toLowerCase()));
  }
}
