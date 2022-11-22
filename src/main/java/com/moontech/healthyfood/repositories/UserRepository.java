package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
