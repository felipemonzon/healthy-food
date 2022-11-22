/*
 * Copyright (c) 2021 Sano Pak
 *
 * Licensed under the GNU General Public License, Version 3 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * https://www.gnu.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.moontech.healthyfood.business;

import com.moontech.healthyfood.constants.ErrorConstant;
import com.moontech.healthyfood.entities.UnitEntity;
import com.moontech.healthyfood.exceptions.custom.BusinessException;
import com.moontech.healthyfood.exceptions.custom.NotDataFoundException;
import com.moontech.healthyfood.models.requests.UnitRequest;
import com.moontech.healthyfood.models.responses.UnitResponse;
import com.moontech.healthyfood.repositories.UnitRepository;
import com.moontech.healthyfood.services.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de las reglas de negocio del módulo de unidades.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UnitBusiness implements UnitService {
  /** Repositorio de unidades. */
  private final UnitRepository unitRepository;

  /** {@inheritDoc}. */
  @Override
  public List<UnitResponse> retrieve() {
    log.info("Consulta todas las unidades de medida");
    return this.unitRepository.findAll().stream().map(this::mapping).collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public List<UnitResponse> findBy(String search) {
    log.debug("Consulta una unidad de medida por {}", search);
    return this.unitRepository.findBy(search).stream()
        .map(this::mapping)
        .collect(Collectors.toList());
  }

  /** {@inheritDoc}. */
  @Override
  public void save(UnitRequest request) {
    log.debug("Guarda una unidad de medida {}", request);
    UnitEntity entity = this.unitRepository.findByName(request.getName());
    if (ObjectUtils.isEmpty(entity)) {
      this.unitRepository.save(this.mapping(request));
    } else {
      throw new BusinessException(
          ErrorConstant.REGISTER_DATA_EXIST_CODE, ErrorConstant.UNIT_REGISTER_MESSAGE);
    }
  }

  /** {@inheritDoc}. */
  @Override
  public void update(Long id, UnitRequest request) {
    this.validateUnit(id);
    request.setId(id);
    log.debug("Actualiza los datos de una unidad de medida {}", request);
    this.unitRepository.save(this.mapping(request));
  }

  /** {@inheritDoc}. */
  @Override
  public void delete(Long id) {
    log.debug("Elimina una unidad por su identificador {}", id);
    UnitEntity entity = this.validateUnit(id);
    entity.setStatus(false);
    this.unitRepository.save(entity);
  }

  /**
   * Válida si unidad ya está registrada.
   *
   * @param id identificador de la unidad
   * @return entidad de unidad {@link UnitEntity}
   */
  private UnitEntity validateUnit(Long id) {
    return this.unitRepository
        .findById(id)
        .<NotDataFoundException>orElseThrow(
            () -> {
              throw new NotDataFoundException(ErrorConstant.UNIT_NOT_FOUND_MESSAGE);
            });
  }

  /**
   * Convierte un entity de tipo {@link UnitEntity} a un objeto {@link UnitResponse}
   *
   * @param entity entidad de unidades
   * @return {@link UnitResponse} respuesta de la API de unidades
   */
  private UnitResponse mapping(UnitEntity entity) {
    return new ModelMapper().map(entity, UnitResponse.class);
  }

  /**
   * Convierte un objeto {@link UnitRequest} a un entity de tipo {@link UnitEntity}
   *
   * @param request petición con los datos de la unidad
   * @return {@link UnitEntity} entidad de unidades
   */
  private UnitEntity mapping(UnitRequest request) {
    return new ModelMapper().map(request, UnitEntity.class);
  }
}
