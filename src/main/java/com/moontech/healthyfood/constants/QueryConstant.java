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
package com.moontech.healthyfood.constants;

/**
 * Consultas estáticas.
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
public class QueryConstant {
  /**
   * Consulta usuario por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>E-mail
   *   <li>teléfono
   * </ul>
   */
  public static final String FIND_USER_BY =
      "SELECT u.id, u.name, u.email, u.phone, u.active, u.id_branch_office, u.created_by, u.created_date, u.updated_by, u.updated_date, u.password "
          + "FROM user u "
          + "WHERE u.name LIKE %:search% "
          + "OR u.email LIKE %:search% "
          + "OR u.phone LIKE %:search% ";
  /**
   * Consulta sucursal por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Teléfono
   *   <li>Coordenadas
   *   <li>Dirección
   * </ul>
   */
  public static final String FIND_OFFICES_BY =
      "SELECT b.id, b.name, b.phone, b.address, b.active, b.manager, created_by, created_date, updated_by, updated_date "
          + "FROM offices b "
          + "WHERE b.name LIKE %:search% "
          + "OR b.phone LIKE %:search% "
          + "OR b.address LIKE %:search%";
  /**
   * Consulta perfile por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Valor
   * </ul>
   */
  public static final String FIND_PROFILES_BY =
      "SELECT r.id, r.name, r.value "
          + "FROM roles r "
          + "WHERE r.name LIKE %:search% "
          + "OR r.value LIKE %:search%";
  /**
   * Consulta proveedores por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Teléfono
   *   <li>Dirección
   * </ul>
   */
  public static final String FIND_SUPPLIERS_BY =
      "SELECT s.id, s.name, s.phone, s.address, s.enterprise, s.rfc, s.status, s.comments, s.created_by, s.created_date, s.updated_by, s.updated_date "
          + "FROM suppliers s "
          + "WHERE s.name LIKE %:search% "
          + "OR s.phone LIKE %:search% "
          + "OR s.address LIKE %:search%";
  /**
   * Consulta parámetros por:
   *
   * <ul>
   *   <li>Descripción
   *   <li>Clave
   *   <li>Valor
   * </ul>
   */
  public static final String FIND_PARAMETERS_BY =
      "SELECT p.id, p.description, p.value, p.status, p.created_by, p.created_date, p.updated_by, p.updated_date "
          + "FROM parameters p "
          + "WHERE p.id LIKE %:search% "
          + "OR p.description LIKE %:search% "
          + "OR p.value LIKE %:search%";
  /**
   * Consulta unidades por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Abreviación
   * </ul>
   */
  public static final String FIND_UNITS_BY =
      "SELECT u.id, u.name, u.abbreviation, u.status, u.created_by, u.created_date, u.updated_by, u.updated_date "
          + "FROM units u "
          + "WHERE u.name LIKE %:search% "
          + "OR u.abbreviation LIKE %:search% ";
  /**
   * Consulta ingredientes por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Proveedor
   * </ul>
   */
  public static final String FIND_INGREDIENT_BY =
      "SELECT i.id, i.name, i.supplier, i.quantity, i.unit, i.raw, i.cooked, i.calories, i.fat, i.carbohydrates, "
          + "i.protein, i.cost, i.status, i.created_by, i.created_date, i.updated_by, i.updated_date "
          + "FROM ingredients i "
          + "INNER JOIN suppliers s ON s.id = i.supplier "
          + "WHERE i.name LIKE %:search% "
          + "OR s.name LIKE %:search%";

  /**
   * Consulta colonias por:
   *
   * <ul>
   *   <li>Nombre
   *   <li>Código Postal
   * </ul>
   */
  public static final String FIND_SUBURB_BY =
      "SELECT s.id, s.name, s.postal_code, s.delivery_cost, s.id_municipality, s.status, s.created_by, s.created_date, s.updated_by, s.updated_date "
          + "FROM suburbs s "
          + "INNER JOIN municipalities m ON m.id = s.id_municipality "
          + "INNER JOIN states st ON st.id = m.id_state "
          + "WHERE s.name LIKE %:search% "
          + "OR s.postal_code LIKE %:search% "
          + "OR s.delivery_cost = :search "
          + "OR m.name LIKE %:search% "
          + "OR st.name LIKE %:search%";
  /** Parámetro de búsqueda. */
  public static final String SEARCH_PARAMETER = "search";
  /**
   * Consulta platillos que se encuentren agendados entre la fecha de mañana y fecha de expiración
   * de la suscripción.
   */
  public static final String FIND_SCHEDULE_DISHES_BETWEEN =
      "SELECT scheduleDishes FROM ScheduleDishesEntity scheduleDishes "
          + "WHERE Date(scheduleDishes.date) BETWEEN :tomorrow AND :expiredSubscription";
  /** Parámetro para la fecha de mañana. */
  public static final String TOMORROW_PARAMETER = "tomorrow";
  /** Parámetro para la fecha de expiración de la suscripción. */
  public static final String EXPIRED_SUBSCRIPTION_PARAMETER = "expiredSubscription";

  /** constructor privado. */
  private QueryConstant() {}
}
