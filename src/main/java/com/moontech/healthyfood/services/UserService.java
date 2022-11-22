package com.moontech.healthyfood.services;

import com.moontech.healthyfood.models.responses.InitialUserResponse;
import com.moontech.healthyfood.models.responses.UserResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * Reglas de negocio de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
public interface UserService {
  /**
   * Consulta los usuarios por página.
   *
   * @param pageable página a consultar
   * @return lista de usuarios
   */
  List<UserResponse> retrieve(Pageable pageable);

  /**
   * Consulta los datos para iniciar la pantalla de usuarios.
   *
   * @return {@code InitialUserResponse}
   */
  InitialUserResponse initialUser();
}
