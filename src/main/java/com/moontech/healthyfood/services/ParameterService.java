package com.moontech.healthyfood.services;

import com.moontech.healthyfood.models.requests.ParameterRequest;
import com.moontech.healthyfood.models.responses.ParameterResponse;
import java.util.List;

/**
 * Reglas de negocio del módulo de parámetros.
 *
 * @author Felipe Monzón
 * @enterprise Sano Pak
 * @since 23 jul., 2022
 */
public interface ParameterService {
  /**
   * Consulta todos los parámetros.
   *
   * @return lista con los parámetros encontrados
   */
  List<ParameterResponse> retrieve();

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
   * @return lista con los parámetros encontrados
   */
  List<ParameterResponse> findBy(String search);

  /**
   * Registra un parámetro.
   *
   * @param request datos del parámetro a registrar
   */
  void save(ParameterRequest request);

  /**
   * Actualiza los datos un parámetro.
   *
   * @param id identificador del parámetro
   * @param request datos del parámetro a registrar
   */
  void update(String id, ParameterRequest request);

  /**
   * Elimina un parámetro.
   *
   * @param id identificador del parámetro
   */
  void delete(String id);
}
