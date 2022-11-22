package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.ParameterRequest;
import com.moontech.healthyfood.models.responses.ParameterResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.ParameterService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * APIs de parámetros.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.PARAMETER_BASE_PATH)
public class ParameterController {
  /** Servicio de parámetros. */
  private final ParameterService parameterService;

  /**
   * Consulta todos los parámetros.
   *
   * @return lista de parámetros
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<List<ParameterResponse>> retrieve() {
    return ResponseEntity.ok(this.parameterService.retrieve());
  }

  /**
   * API parameters-search
   *
   * @return lista de parámetros encontrados
   */
  @GetMapping(path = RoutesConstant.SEARCH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<List<ParameterResponse>> findBy(@PathVariable String search) {
    return ResponseEntity.ok(this.parameterService.findBy(search.trim()));
  }

  /**
   * Guarda los datos de un parámetro.
   *
   * @return 204 si se registro correctamente
   */
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> save(@RequestBody @Valid ParameterRequest request) {
    this.parameterService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * API parameters-data-update
   *
   * @return 204 si se actualizó correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PutMapping(path = RoutesConstant.DATA_MODIFIED_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(
      @PathVariable String id, @RequestBody @Valid ParameterRequest request) {
    this.parameterService.update(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Elimina un parámetro.
   *
   * @return 204 si se elimino correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @DeleteMapping(path = RoutesConstant.DATA_MODIFIED_PATH)
  public ResponseEntity<Void> delete(@PathVariable String id) {
    this.parameterService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
