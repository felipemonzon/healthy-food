package com.moontech.library.entities;

import com.moontech.library.constants.DatabaseConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Entidad para la tabla "proveedores".
 *
 * @author Felipe Monzón
 * @enterprise moontech
 * @since 23 jul., 2022
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = DatabaseConstant.TABLE_SUPPLIERS)
public class SupplierEntity extends AuditableEntity {
  /** Identificador del proveedor. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /** Nombre del proveedor. */
  @NaturalId
  @Column(
      name = DatabaseConstant.PROPERTY_SUPPLIERS_NAME,
      nullable = false,
      length = 50,
      unique = true)
  private String name;
  /** Propiedad para la empresa. */
  @Column(name = DatabaseConstant.PROPERTY_SUPPLIERS_ENTERPRISE, nullable = false, length = 50)
  private String enterprise;
  /** Propiedad para el RFC. */
  @NaturalId(mutable = true)
  @Column(
      name = DatabaseConstant.PROPERTY_SUPPLIERS_RFC,
      nullable = false,
      unique = true,
      length = 20)
  private String rfc;
  /** Dirección del proveedor. */
  @Column(name = DatabaseConstant.PROPERTY_ADDRESS, nullable = false, length = 100)
  private String address;
  /** Teléfono del proveedor. */
  @Column(name = DatabaseConstant.PROPERTY_PHONE, nullable = false, length = 10)
  private String phone;
  /** Comentarios. */
  @Column(name = DatabaseConstant.PROPERTY_SUPPLIERS_COMMENTS, nullable = false, length = 200)
  private String comments;
  /** Status del proveedor. */
  @Column(name = DatabaseConstant.PROPERTY_STATUS)
  private boolean status;
}
