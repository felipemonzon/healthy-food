package com.moontech.healthyfood.apis;

import com.moontech.healthyfood.constants.RoutesConstant;
import com.moontech.healthyfood.models.responses.InitialUserResponse;
import com.moontech.healthyfood.models.responses.UserResponse;
import com.moontech.healthyfood.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserResponse>> retrieve(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(this.userService.retrieve(pageable));
  }

  /**
   * Recurso para cargar los datos iniciales para la página de usuarios.
   *
   * @return lista de sucursales y perfiles.
   */
  @GetMapping(path = RoutesConstant.INITIAL_DATA_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InitialUserResponse> initialUserPage() {
    return ResponseEntity.ok(this.userService.initialUser());
  }
}
