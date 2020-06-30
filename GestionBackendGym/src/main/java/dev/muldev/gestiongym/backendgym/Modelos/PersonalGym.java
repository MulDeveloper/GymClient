/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

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
@Table(name = "personal_gym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalGym.findAll", query = "SELECT p FROM PersonalGym p"),
    @NamedQuery(name = "PersonalGym.findByIdpersonal", query = "SELECT p FROM PersonalGym p WHERE p.idpersonal = :idpersonal"),
    @NamedQuery(name = "PersonalGym.findByNombrePersonal", query = "SELECT p FROM PersonalGym p WHERE p.nombrePersonal = :nombrePersonal"),
    @NamedQuery(name = "PersonalGym.findByApellidoPersonal", query = "SELECT p FROM PersonalGym p WHERE p.apellidoPersonal = :apellidoPersonal"),
    @NamedQuery(name = "PersonalGym.findByEmailPersonal", query = "SELECT p FROM PersonalGym p WHERE p.emailPersonal = :emailPersonal"),
    @NamedQuery(name = "PersonalGym.findByNifPersonal", query = "SELECT p FROM PersonalGym p WHERE p.nifPersonal = :nifPersonal"),
    @NamedQuery(name = "PersonalGym.findByTelPersonal", query = "SELECT p FROM PersonalGym p WHERE p.telPersonal = :telPersonal"),
    @NamedQuery(name = "PersonalGym.findByDireccionPersonal", query = "SELECT p FROM PersonalGym p WHERE p.direccionPersonal = :direccionPersonal"),
    @NamedQuery(name = "PersonalGym.findBySueldoBruto", query = "SELECT p FROM PersonalGym p WHERE p.sueldoBruto = :sueldoBruto"),
    @NamedQuery(name = "PersonalGym.findByFechaNacimiento", query = "SELECT p FROM PersonalGym p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "PersonalGym.findByFechaAlta", query = "SELECT p FROM PersonalGym p WHERE p.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "PersonalGym.findByRol", query = "SELECT p FROM PersonalGym p WHERE p.rol = :rol")})
public class PersonalGym implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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

    public PersonalGym() {
    }

    public PersonalGym(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getApellidoPersonal() {
        return apellidoPersonal;
    }

    public void setApellidoPersonal(String apellidoPersonal) {
        this.apellidoPersonal = apellidoPersonal;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public String getNifPersonal() {
        return nifPersonal;
    }

    public void setNifPersonal(String nifPersonal) {
        this.nifPersonal = nifPersonal;
    }

    public Integer getTelPersonal() {
        return telPersonal;
    }

    public void setTelPersonal(Integer telPersonal) {
        this.telPersonal = telPersonal;
    }

    public String getDireccionPersonal() {
        return direccionPersonal;
    }

    public void setDireccionPersonal(String direccionPersonal) {
        this.direccionPersonal = direccionPersonal;
    }

    public BigDecimal getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(BigDecimal sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonal != null ? idpersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalGym)) {
            return false;
        }
        PersonalGym other = (PersonalGym) object;
        if ((this.idpersonal == null && other.idpersonal != null) || (this.idpersonal != null && !this.idpersonal.equals(other.idpersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.PersonalGym[ idpersonal=" + idpersonal + " ]";
    }
    
}
