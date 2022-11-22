package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.SupplierRequest;
import com.moontech.healthyfood.models.responses.SupplierResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * APIS de proveedores.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.SUPPLIER_BASE_PATH)
public class SupplierController {
  /** Servicio de proveedores. */
  private final SupplierService supplierService;

  /**
   * Consulta todos los proveedores.
   *
   * @return lista de proveedores encontrados
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<List<SupplierResponse>> retrieve() {
    return ResponseEntity.ok(this.supplierService.retrieve());
  }

  /**
   * Busca un proveedor por nombre, dirección, teléfono
   *
   * @return lista de proveedores encontrados
   */
  @GetMapping(path = RoutesConstant.SEARCH_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<List<SupplierResponse>> findBy(
      @PathVariable @Valid @NotBlank String search) {
    return ResponseEntity.ok(this.supplierService.findBy(search.trim()));
  }

  /**
   * API supplier-data-save
   *
   * @return Http 201 si se registro correctamente
   */
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> save(@RequestBody @Valid SupplierRequest request) {
    this.supplierService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Actualiza los datos de un proveedor
   *
   * @return Http 204 si se elimino correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PutMapping(path = RoutesConstant.DATA_MODIFIED_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> findBy(
      @PathVariable @Valid @Min(1) Long id, @RequestBody @Valid SupplierRequest request) {
    this.supplierService.update(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Elimina un proveedor
   *
   * @return Http 204 si se elimino correctamente
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @DeleteMapping(path = RoutesConstant.DATA_MODIFIED_PATH)
  public ResponseEntity<Void> delete(@PathVariable @Valid @Min(1) Long id) {
    this.supplierService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
