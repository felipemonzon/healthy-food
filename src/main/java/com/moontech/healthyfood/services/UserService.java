package com.moontech.healthyfood.services;

import com.moontech.healthyfood.models.requests.UserRequest;
import com.moontech.healthyfood.models.responses.InitialUserResponse;
import com.moontech.healthyfood.models.responses.UserResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

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

  /**
   * Actualiza los datos de perfil del usuario que inicio sesión.
   *
   * @param auth {@code Authentication}
   * @param request {@code UserRequest}
   * @return datos del usuario
   */
  UserResponse updateUserProfile(Authentication auth, UserRequest request);

  /**
   * Consulta usuarios por
   *
   * <ul>
   *   <li>Nombre
   *   <li>E-mail
   *   <li>teléfono
   * </ul>
   *
   * @param search parámetro de búsqueda
   * @return lista de usuarios
   */
  List<UserResponse> findBy(String search);

  /**
   * Actualiza los datos del usuario
   *
   * @param id identificador del usuario
   * @param request datos del usuario
   */
  void update(Long id, UserRequest request);

  /**
   * Guarda los datos de un usuario.
   *
   * @param request {@code UserRequest}
   */
  void save(UserRequest request);
}
