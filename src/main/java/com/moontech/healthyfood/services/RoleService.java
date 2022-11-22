package com.moontech.healthyfood.services;

import com.moontech.healthyfood.models.requests.ProfileRequest;
import com.moontech.healthyfood.models.responses.AuthorityResponse;
import java.util.List;

/**
 * Interface con las reglas de negocio para perfiles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 nov., 2022
 */
public interface RoleService {
  /**
   * Consulta todos los perfiles.
   *
   * @return {@code AuthorityResponse}
   */
  List<AuthorityResponse> retrieve();

  /**
   * Guarda un perfile
   *
   * @param request {@code ProfileRequest}
   */
  void save(ProfileRequest request);

  /**
   * Actualiza los datos del perfil
   *
   * @param id identificador del perfil
   * @param request {@code ProfileRequest}
   */
  void update(Long id, ProfileRequest request);

  /**
   * Consulta perfiles por nombre o valor
   *
   * @param search parámetro de búsqueda
   * @return lista de perfiles encontrados
   */
  List<AuthorityResponse> findBy(final String search);
}
