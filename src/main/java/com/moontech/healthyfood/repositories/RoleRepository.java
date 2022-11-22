package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para perfiles o roles.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 nov., 2022
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  /**
   * Consulta perfil por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Valor
   * </ul>
   *
   * @param search parámetro de consulta
   * @return lista de perfiles encontrados {@link RoleEntity}
   */
  @Query(nativeQuery = true, value = QueryConstant.FIND_PROFILES_BY)
  List<RoleEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);
}
