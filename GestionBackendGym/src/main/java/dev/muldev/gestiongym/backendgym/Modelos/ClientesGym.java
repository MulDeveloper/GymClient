/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "clientes_gym")
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQueries({
    @NamedQuery(name = "ClientesGym.findAll", query = "SELECT c FROM ClientesGym c"),
    @NamedQuery(name = "ClientesGym.findByIdcliente", query = "SELECT c FROM ClientesGym c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "ClientesGym.findByNombreCliente", query = "SELECT c FROM ClientesGym c WHERE c.nombreCliente = :nombreCliente"),
    @NamedQuery(name = "ClientesGym.findByApellidoCliente", query = "SELECT c FROM ClientesGym c WHERE c.apellidoCliente = :apellidoCliente"),
    @NamedQuery(name = "ClientesGym.findByEmailCliente", query = "SELECT c FROM ClientesGym c WHERE c.emailCliente = :emailCliente"),
    @NamedQuery(name = "ClientesGym.findByNifCliente", query = "SELECT c FROM ClientesGym c WHERE c.nifCliente = :nifCliente"),
    @NamedQuery(name = "ClientesGym.findByTelCliente", query = "SELECT c FROM ClientesGym c WHERE c.telCliente = :telCliente"),
    @NamedQuery(name = "ClientesGym.findByDireccionCliente", query = "SELECT c FROM ClientesGym c WHERE c.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "ClientesGym.findByIdMatricula", query = "SELECT c FROM ClientesGym c WHERE c.idMatricula = :idMatricula"),
    @NamedQuery(name = "ClientesGym.findByFechaNacimiento", query = "SELECT c FROM ClientesGym c WHERE c.fechaNacimiento = :fechaNacimiento")})
public class ClientesGym implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "nombre_cliente")
    private String nombreCliente;
    @Column(name = "apellido_cliente")
    private String apellidoCliente;
    @Column(name = "email_cliente")
    private String emailCliente;
    @Column(name = "nif_cliente")
    private String nifCliente;
    @Column(name = "tel_cliente")
    private Integer telCliente;
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Column(name = "id_matricula")
    private Integer idMatricula;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaNacimiento;

    public ClientesGym() {
    }

    public ClientesGym(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(String nifCliente) {
        this.nifCliente = nifCliente;
    }

    public Integer getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(Integer telCliente) {
        this.telCliente = telCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesGym)) {
            return false;
        }
        ClientesGym other = (ClientesGym) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.ClientesGym[ idcliente=" + idcliente + " ]";
    }

    
}
