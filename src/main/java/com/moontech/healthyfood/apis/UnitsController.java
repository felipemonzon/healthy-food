package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.UnitRequest;
import com.moontech.healthyfood.models.responses.UnitResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * APIS de unidades de medida.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.UNITS_BASE_PATH)
public class UnitsController {
  /** Servicio de unidades. */
  private final UnitService unitService;

  /**
   * Consulta todas las unidades de medida.
   *
   * @return lista de unidades
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UnitResponse>> retrieve() {
    return ResponseEntity.ok(this.unitService.retrieve());
  }

  /**
   * Consulta los datos de una unidad de medida.
   *
   * @return lista de unidades encontradas
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(path = RoutesConstant.SEARCH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UnitResponse>> findBy(@PathVariable String search) {
    return ResponseEntity.ok(this.unitService.findBy(search));
  }

  /**
   * API unit-save
   *
   * @return 204 si se registro correctamente
   */
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> save(@RequestBody @Valid UnitRequest request) {
    this.unitService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Actualiza los datos de una unidad de medida.
   *
   * @return 204 si se actualizó correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PutMapping(path = RoutesConstant.DATA_MODIFIED_PATH, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(
      @PathVariable Long id, @RequestBody @Valid UnitRequest request) {
    this.unitService.update(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Elimina una unidad de medida.
   *
   * @return 204 si se elimino correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @DeleteMapping(path = RoutesConstant.DATA_MODIFIED_PATH)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.unitService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
