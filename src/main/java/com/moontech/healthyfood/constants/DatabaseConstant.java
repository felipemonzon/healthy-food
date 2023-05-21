package com.moontech.healthyfood.constants;

/**
 * Constantes para la base de datos.
 *
 * @author Felipe Monzón
 * @since 23 jul., 2022
 */
public abstract class DatabaseConstant {
  /** Propiedad para el nombre de la tabla "users". */
  public static final String USERS_TABLE = "users";
  /** Propiedad para el campo "create_user". */
  public static final String CREATE_USER = "created_by";
  /** Propiedad para el campo "create_date". */
  public static final String CREATE_DATE = "created_date";
  /** Propiedad para el campo "last modified_user". */
  public static final String LAST_MODIFIED_USER = "updated_by";
  /** Propiedad para el campo "last modified_date". */
  public static final String LAST_MODIFIED_DATE = "updated_date";
  /** Propiedad para primer nombre. */
  public static final String FIRST_NAME_PROPERTY = "first_name";
  /** Propiedad para el segundo nombre. */
  public static final String LAST_NAME_PROPERTY = "last_name";
  /** Propiedad para nombre de usuario. */
  public static final String USERNAME_PROPERTY = "username";
  /** Propiedad para contraseña. */
  public static final String PASSWORD_PROPERTY = "password";
  /** Propiedad para el correo. */
  public static final String EMAIL_PROPERTY = "email";
  /** Propiedad para el número de celular. */
  public static final String CELLPHONE_PROPERTY = "phone";
  /** Propiedad para el identificador de la sucursal del usuario. */
  public static final String PROPERTY_USER_BRANCH = "id";
  /** Nombre de la tabla "roles". */
  public static final String TABLE_ROLES = "roles";
  /** Propiedad para el nombre del rol. */
  public static final String PROPERTY_ROLE_NAME = "name";
  /** Propiedad para el valor del rol. */
  public static final String PROPERTY_ROLE_VALUE = "value";
  /** Propiedad para la relación usuarios - roles. */
  public static final String RELATION_USER_ROLES_NAME = "user_roles";
  /** Propiedad para el identificador del rol. */
  public static final String PROPERTY_ROL_ID = "id_role";
  /** Propiedad para el identificador del usuario. */
  public static final String PROPERTY_USER_ID = "id_user";
  /** Nombre de la tabla "sucursales". */
  public static final String TABLE_OFFICES = "offices";
  /** Propiedad para el identificador de la sucursal. */
  public static final String PROPERTY_OFFICE_ID = "id_office";
  /** Propiedad para el nombre de la sucursal. */
  public static final String PROPERTY_OFFICE_NAME = "name";
  /** Propiedad para el status de la sucursal. */
  public static final String PROPERTY_OFFICE_STATUS = "active";
  /** Propiedad para la relación sucursales - usuarios. */
  public static final String RELATION_OFFICE_USERS_NAME = "branchOffice";
  /** Propiedad para la dirección. */
  public static final String PROPERTY_ADDRESS = "address";
  /** Propiedad para el teléfono. */
  public static final String PROPERTY_PHONE = "phone";
  /** Propiedad para el encargado de la sucursal. */
  public static final String PROPERTY_OFFICE_MANAGER = "manager";
  /** Nombre de la tabla "proveedores". */
  public static final String TABLE_SUPPLIERS = "suppliers";
  /** Propiedad para el nombre del proveedor. */
  public static final String PROPERTY_SUPPLIERS_NAME = "name";
  /** Propiedad para la razón social del proveedor. */
  public static final String PROPERTY_SUPPLIERS_ENTERPRISE = "enterprise";
  /** Propiedad para el RFC del proveedor. */
  public static final String PROPERTY_SUPPLIERS_RFC = "rfc";
  /** Propiedad para los comentarios del proveedor. */
  public static final String PROPERTY_SUPPLIERS_COMMENTS = "comments";
  /** Propiedad para el status. */
  public static final String PROPERTY_STATUS = "status";
  /** Nombre de la tabla "parámetros". */
  public static final String TABLE_PARAMETERS = "parameters";
  /** Propiedad para el identificador del parámetro. */
  public static final String PROPERTY_PARAMETER_ID = "id";
  /** Propiedad para el nombre del parámetro. */
  public static final String PROPERTY_PARAMETER_NAME = "description";
  /** Propiedad para el valor del parámetro. */
  public static final String PROPERTY_PARAMETER_VALUE = "value";
  /** Nombre de la tabla "units". */
  public static final String TABLE_UNITS = "units";
  /** Propiedad para el nombre de la unidad parámetro. */
  public static final String PROPERTY_UNIT_NAME = "name";
  /** Propiedad para la abreviación de la unidad parámetro. */
  public static final String PROPERTY_UNIT_ABBREVIATION = "abbreviation";
  /** Nombre de la tabla "citas". */
  public static final String TABLE_APPOINTMENT = "appointments";
  /** Propiedad para el título de la cita. */
  public static final String PROPERTY_APPOINTMENT_NAME = "title";
  /** Propiedad para el inicio de la cita. */
  public static final String PROPERTY_APPOINTMENT_START_DATE = "start_date";
  /** Propiedad para el final de la cita. */
  public static final String PROPERTY_APPOINTMENT_END_DATE = "end_date";
  /** Propiedad para la relación sucursales - usuarios. */
  public static final String RELATION_APPOINTMENT_CUSTOMER_NAME = "customer";
  /** Nombre de la tabla "clientes". */
  public static final String TABLE_CUSTOMER = "customers";
  /** Propiedad para el identificador del cliente. */
  public static final String PROPERTY_CUSTOMER_ID = "id_customer";
  /** Propiedad para el identificador de la cita del cliente. */
  public static final String PROPERTY_APPOINTMENT = "id";

  /** Constructor privado. */
  private DatabaseConstant() {}
}
