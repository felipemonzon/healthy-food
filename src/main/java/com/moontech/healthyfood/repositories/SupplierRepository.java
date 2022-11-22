package com.moontech.healthyfood.repositories;

import com.moontech.healthyfood.constants.QueryConstant;
import com.moontech.healthyfood.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repositorio para proveedores.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
  /**
   * Consulta proveedores por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Teléfono
   *   <li>Dirección
   * </ul>
   *
   * @param search parámetro de consulta
   * @return lista de proveedores encontrados {@link SupplierEntity}
   */
  @Query(nativeQuery = true, value = QueryConstant.FIND_SUPPLIERS_BY)
  List<SupplierEntity> findBy(@Param(QueryConstant.SEARCH_PARAMETER) String search);
}
