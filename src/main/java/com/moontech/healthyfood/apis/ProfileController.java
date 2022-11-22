package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.ProfileRequest;
import com.moontech.healthyfood.models.responses.AuthorityResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * APIS para perfiles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 19 nov., 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.PROFILE_BASE_PATH)
public class ProfileController {
  /** Servicio de perfiles. */
  private final RoleService roleService;

  /**
   * Consulta lor perfiles disponibles.
   *
   * @return lista de perfiles
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AuthorityResponse>> retrieve() {
    return ResponseEntity.ok(this.roleService.retrieve());
  }

  /**
   * Guarda los datos de un perfil.
   *
   * @param request {@code ProfileRequest}
   * @return 201 si se creo correctamente el perfil
   */
  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> save(@RequestBody @Valid ProfileRequest request) {
    this.roleService.save(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Actualiza los datos de un perfil
   *
   * @param id identificador del perfil
   * @param request {@code ProfileRequest}
   * @return 204 si se actualizó con éxito
   */
  @PutMapping(
      path = RoutesConstant.DATA_MODIFIED_PATH,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  public ResponseEntity<Void> update(
      @PathVariable @NotNull @Min(value = 1) Long id, @RequestBody @Valid ProfileRequest request) {
    this.roleService.update(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
