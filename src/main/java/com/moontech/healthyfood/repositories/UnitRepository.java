package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de usuarios.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
  /**
   * Busca una unidad por su nombre.
   *
   * @param name nombre de la unidad
   * @return unidad encontrada
   */
  UnitEntity findByName(String name);

  /**
   * Consulta unidades por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Abreviación
   * </ul>
   *
   * @param search parámetro de consulta
   * @return lista de unidades encontrada {@link UnitEntity}
   */
  @Query(nativeQuery = true, value = QueryConstant.FIND_UNITS_BY)
  List<UnitEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);
}
