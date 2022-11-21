package com.moontech.library.services;

import com.moontech.library.models.requests.UnitRequest;
import com.moontech.library.models.responses.UnitResponse;

import java.util.List;

/**
 * Reglas de negocio del módulo de unidades.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
public interface UnitService {
  /**
   * Consulta todas las unidades.
   *
   * @return lista de todas las unidades
   */
  List<UnitResponse> retrieve();

  /**
   * Consultas las unidades por:
   *
   * <ul>
   *   *
   *   <li>Id
   *   <li>Nombre
   *   <li>Abreviación
   * </ul>
   *
   * @param search parámetro de búsqueda
   * @return lista de unidades encontradas
   */
  List<UnitResponse> findBy(String search);

  /**
   * Registra una unidad.
   *
   * @param request datos de la unidad {@link UnitRequest}
   */
  void save(UnitRequest request);

  /**
   * Actualiza los datos de una unidad.
   *
   * @param id identificador de la unidad
   * @param request datos de la unidad {@link UnitRequest}
   */
  void update(Long id, UnitRequest request);

  /**
   * Elimina una unidad.
   *
   * @param id identificador de la unidad
   */
  void delete(Long id);
}
