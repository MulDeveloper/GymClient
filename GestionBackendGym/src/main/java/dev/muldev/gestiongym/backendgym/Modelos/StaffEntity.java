/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "staff")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Data
@NamedQueries({
        @NamedQuery(name = "PersonalGym.findAll", query = "SELECT p FROM StaffEntity p"),
        @NamedQuery(name = "PersonalGym.findByIdpersonal", query = "SELECT p FROM StaffEntity p WHERE p.idpersonal = :idpersonal"),
        @NamedQuery(name = "PersonalGym.findByNombrePersonal", query = "SELECT p FROM StaffEntity p WHERE p.nombrePersonal = :nombrePersonal"),
        @NamedQuery(name = "PersonalGym.findByApellidoPersonal", query = "SELECT p FROM StaffEntity p WHERE p.apellidoPersonal = :apellidoPersonal"),
        @NamedQuery(name = "PersonalGym.findByEmailPersonal", query = "SELECT p FROM StaffEntity p WHERE p.emailPersonal = :emailPersonal"),
        @NamedQuery(name = "PersonalGym.findByNifPersonal", query = "SELECT p FROM StaffEntity p WHERE p.nifPersonal = :nifPersonal"),
        @NamedQuery(name = "PersonalGym.findByTelPersonal", query = "SELECT p FROM StaffEntity p WHERE p.telPersonal = :telPersonal"),
        @NamedQuery(name = "PersonalGym.findByDireccionPersonal", query = "SELECT p FROM StaffEntity p WHERE p.direccionPersonal = :direccionPersonal"),
        @NamedQuery(name = "PersonalGym.findBySueldoBruto", query = "SELECT p FROM StaffEntity p WHERE p.sueldoBruto = :sueldoBruto"),
        @NamedQuery(name = "PersonalGym.findByFechaNacimiento", query = "SELECT p FROM StaffEntity p WHERE p.fechaNacimiento = :fechaNacimiento"),
        @NamedQuery(name = "PersonalGym.findByFechaAlta", query = "SELECT p FROM StaffEntity p WHERE p.fechaAlta = :fechaAlta"),
        @NamedQuery(name = "PersonalGym.findByRol", query = "SELECT p FROM StaffEntity p WHERE p.rol = :rol"),
        @NamedQuery(name = "PersonalGym.sumaSueldos", query = "SELECT sum(p.sueldoBruto) FROM StaffEntity p")})
public class StaffEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonal")
    private Integer idpersonal;
    @Column(name = "nombre_personal")
    private String nombrePersonal;
    @Column(name = "apellido_personal")
    private String apellidoPersonal;
    @Column(name = "email_personal")
    private String emailPersonal;
    @Column(name = "nif_personal")
    private String nifPersonal;
    @Column(name = "tel_personal")
    private Integer telPersonal;
    @Column(name = "direccion_personal")
    private String direccionPersonal;
    @Column(name = "sueldo_bruto")
    private BigDecimal sueldoBruto;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "rol")
    private String rol;


}
