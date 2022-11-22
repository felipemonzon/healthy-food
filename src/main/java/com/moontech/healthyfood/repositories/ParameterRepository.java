package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio de parámetros.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
public interface ParameterRepository extends JpaRepository<ParameterEntity, String> {
  /**
   * Consulta parámetros por:
   *
   * <ul>
   *   <li>Descripción
   *   <li>Clave
   *   <li>Valor
   * </ul>
   *
   * @param search parámetro a consultar
   * @return lista de parámetros encontrados.
   */
  @Query(nativeQuery = true, value = QueryConstant.FIND_PARAMETERS_BY)
  List<ParameterEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);

  /**
   * Consulta parámetros de una categoría.
   *
   * @param ids lista de ids a consultar
   * @return lista de parámetros.
   */
  List<ParameterEntity> findByIdIn(List<String> ids);
}
