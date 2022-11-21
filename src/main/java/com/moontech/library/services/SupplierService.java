package com.moontech.library.services;

import com.moontech.library.models.requests.SupplierRequest;
import com.moontech.library.models.responses.SupplierResponse;

import java.util.List;

/**
 * Reglas de negocio del módulo de proveedores.
 *
 * @author Felipe Monzón
 * @enterprise SanoPak
 * @since 15/09/21
 */
public interface SupplierService {
  /**
   * Consulta todos los proveedores.
   *
   * @return lista de proveedores {@link SupplierResponse}
   */
  List<SupplierResponse> retrieve();

  /**
   * Consulta proveedores por:
   *
   * <ul>
   *   *
   *   <li>Nombre
   *   <li>Teléfono
   *   <li>Dirección
   * </ul>
   *
   * @param search parámetro de búsqueda
   * @return lista de proveedores {@link SupplierResponse}
   */
  List<SupplierResponse> findBy(String search);

  /**
   * Registra un proveedor.
   *
   * @param request datos del proveedor
   */
  void save(SupplierRequest request);

  /**
   * Actualiza los datos de un proveedor.
   *
   * @param id identificador del proveedor
   * @param request datos del proveedor
   */
  void update(Long id, SupplierRequest request);

  /**
   * Elimina un proveedor.
   *
   * @param id identificador del proveedor
   */
  void delete(Long id);
}
