package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.entities.CustomerEntity;
import com.moontech.healthyfood.exceptions.custom.NotDataFoundException;
import com.moontech.healthyfood.models.requests.CustomerRequest;
import com.moontech.healthyfood.models.responses.CustomerResponse;
import com.moontech.healthyfood.repositories.CustomerRepository;
import com.moontech.healthyfood.services.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de las reglas de negocio para las apis de clientes.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 27 abr., 2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerBusiness implements CustomerService {
  /** Repositorio para clientes. */
  private final CustomerRepository customerRepository;

  /** {@inheritDoc}. */
  @Override
  @Transactional(readOnly = true)
  public List<CustomerResponse> retrieve() {
    log.info("Consulta todos los clientes.");
    return this.customerRepository.findAll().stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  @Transactional
  public void save(CustomerRequest request) {
    log.info("Datos del cliente a guardar {}", request);
    List<CustomerEntity> customers =
        this.customerRepository.findByFirstNameAndLastName(
            request.getFirstName(), request.getLastName());
    if (customers.isEmpty()) {
      this.customerRepository.save(this.mapping(request));
    } else {
      throw new NotDataFoundException(ErrorConstant.RECORD_NOT_FOUND_MESSAGE);
    }
  }

  /**
   * Convierte una entidad {@code CustomerEntity} a uno de tipo {@code CustomerResponse}
   *
   * @param entity objeto de tipo {@link CustomerEntity}
   * @return objeto de salida de la api de clientes
   */
  private CustomerResponse mapping(CustomerEntity entity) {
    return new ModelMapper().map(entity, CustomerResponse.class);
  }

  /**
   * Convierte un objeto {@link CustomerRequest} a un entity de tipo {@link CustomerEntity}
   *
   * @param request petición con los datos del cliente
   * @return {@link CustomerRequest} entidad de clientes
   */
  private CustomerEntity mapping(CustomerRequest request) {
    return new ModelMapper().map(request, CustomerEntity.class);
  }
}
