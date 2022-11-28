package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio de usuarios.
 *
 * @author Felipe Monzón
 * @since 31/12/21
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  /**
   * Consulta un usuario por su nombre de usuario.
   *
   * @param username nombre de usuario
   * @return usuario encontrado o vacío sino se encontró nada
   */
  Optional<UserEntity> findByUsername(String username);

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
  @Query(nativeQuery = true, value = QueryConstant.FIND_USER_BY)
  List<UserEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);
}
