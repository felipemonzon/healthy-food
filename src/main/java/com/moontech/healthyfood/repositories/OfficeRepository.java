package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.OfficeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repositorio para sucursales.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 17 jul., 2022
 */
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {
  /**
   * Consulta sucursal por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Teléfono
   *   <li>Dirección
   * </ul>
   *
   * @param search parámetro de consulta
   * @return lista de sucursales encontrados {@link OfficeEntity}
   */
  @Query(nativeQuery = true, value = QueryConstant.FIND_OFFICES_BY)
  List<OfficeEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);
}
