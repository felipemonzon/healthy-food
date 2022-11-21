package com.moontech.library.services;

import com.moontech.library.models.responses.InitialUserResponse;
import com.moontech.library.models.responses.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
