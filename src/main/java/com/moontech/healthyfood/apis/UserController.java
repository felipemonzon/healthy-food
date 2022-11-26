package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.requests.UserRequest;
import com.moontech.healthyfood.models.responses.InitialUserResponse;
import com.moontech.healthyfood.models.responses.UserResponse;
import com.moontech.healthyfood.security.constants.SecurityConstants;
import com.moontech.healthyfood.services.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * APIS para usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 01 Ene, 2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = RoutesConstant.USERS_BASE_PATH)
public class UserController {
  /** Servicio de usuarios. */
  private final UserService userService;

  /**
   * Consulta todos los usuarios.
   *
   * @param pageable datos de paginación
   * @return lista de usuarios
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserResponse>> retrieve(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(this.userService.retrieve(pageable));
  }

  /**
   * Recurso para cargar los datos iniciales para la página de usuarios.
   *
   * @return lista de sucursales y perfiles.
   */
  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @GetMapping(path = RoutesConstant.INITIAL_DATA_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InitialUserResponse> initialUserPage() {
    return ResponseEntity.ok(this.userService.initialUser());
  }

  @PreAuthorize(SecurityConstants.ADMIN_ALLOWED)
  @PostMapping(
      path = "/profile",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse> updateUserProfile(
      Authentication auth, @RequestBody @Valid UserRequest request) {
    return ResponseEntity.ok(this.userService.updateUserProfile(auth, request));
  }
}
