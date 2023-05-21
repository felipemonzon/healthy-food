package com.moontech.healthyfood.services;

import com.moontech.healthyfood.models.requests.CustomerRequest;
import com.moontech.healthyfood.models.responses.CustomerResponse;
import java.util.List;

/**
 * Reglas de negocio para las APIS de clientes.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 27 abr., 2023
 */
public interface CustomerService {
  /**
   * Consulta todos los clientes.
   *
   * @return lista de clientes encontrados
   */
  List<CustomerResponse> retrieve();

  /**
   * Guarda los datos del cliente.
   *
   * @param request datos del cliente
   */
  void save(CustomerRequest request);
}
