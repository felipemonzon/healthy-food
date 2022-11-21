package com.moontech.library.business;

import com.moontech.library.constants.ErrorConstant;
import com.moontech.library.entities.SupplierEntity;
import com.moontech.library.exceptions.custom.NotDataFoundException;
import com.moontech.library.models.requests.SupplierRequest;
import com.moontech.library.models.responses.SupplierResponse;
import com.moontech.library.repositories.SupplierRepository;
import com.moontech.library.services.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de las reglas de negocio del módulo de proveedores.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierBusiness implements SupplierService {
  /** Repositorio de proveedores. */
  private final SupplierRepository supplierRepository;

  /** {@inheritDoc}. */
  @Override
  public List<SupplierResponse> retrieve() {
    log.info("Consulta todos los proveedores");
    return this.supplierRepository.findAll().stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public List<SupplierResponse> findBy(String search) {
    log.debug("Consulta proveedores por {}", search);
    return this.supplierRepository.findBy(search).stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public void save(SupplierRequest request) {
    log.debug("Proveedor a guardar {}", request);
    this.supplierRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  public void update(Long id, SupplierRequest request) {
    this.validateSupplier(id);
    request.setId(id);
    log.debug("Datos del proveedor a actualizar {}", request);
    this.supplierRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  public void delete(Long id) {
    log.info("Elimina proveedor {}", id);
    SupplierEntity entity = this.validateSupplier(id);
    entity.setStatus(Boolean.FALSE);
    this.supplierRepository.save(entity);
  }

  /**
   * Valida si el proveedor existe.
   *
   * @param id identificador de la sucursal
   * @return entidad del proveedor {@link SupplierEntity}
   */
  private SupplierEntity validateSupplier(Long id) {
    return this.supplierRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.SUPPLIER_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un entity de tipo {@link SupplierEntity} a un objeto {@link SupplierResponse}
   *
   * @param entity entidad de proveedores
   * @return {@link SupplierResponse} respuesta de la API de proveedores
   */
  private SupplierResponse mapping(SupplierEntity entity) {
    return new ModelMapper().map(entity, SupplierResponse.class);
  }

  /**
   * Convierte un objeto {@link SupplierRequest} a un entity de tipo {@link SupplierEntity}
   *
   * @param request petición con los datos de la sucursal
   * @return {@link SupplierEntity} entidad de proveedores
   */
  private SupplierEntity mapping(SupplierRequest request) {
    return new ModelMapper().map(request, SupplierEntity.class);
  }
}
