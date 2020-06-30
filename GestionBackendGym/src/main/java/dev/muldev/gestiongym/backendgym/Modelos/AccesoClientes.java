/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "acceso_clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoClientes.findAll", query = "SELECT a FROM AccesoClientes a"),
    @NamedQuery(name = "AccesoClientes.findByIdacceso", query = "SELECT a FROM AccesoClientes a WHERE a.idacceso = :idacceso"),
    @NamedQuery(name = "AccesoClientes.findByIdcliente", query = "SELECT a FROM AccesoClientes a WHERE a.idcliente = :idcliente"),
    @NamedQuery(name = "AccesoClientes.findByPassword", query = "SELECT a FROM AccesoClientes a WHERE a.password = :password"),
    @NamedQuery(name = "AccesoClientes.findByUsername", query = "SELECT a FROM AccesoClientes a WHERE a.username = :username")})
public class AccesoClientes implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacceso")
    private Integer idacceso;
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @OneToMany(mappedBy = "idcliente")
    private List<GymRoles> gymRolesList;

    public AccesoClientes() {
    }

    public AccesoClientes(Integer idacceso) {
        this.idacceso = idacceso;
    }

    public Integer getIdacceso() {
        return idacceso;
    }

    public void setIdacceso(Integer idacceso) {
        this.idacceso = idacceso;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<GymRoles> getGymRolesList() {
        return gymRolesList;
    }

    public void setGymRolesList(List<GymRoles> gymRolesList) {
        this.gymRolesList = gymRolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacceso != null ? idacceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoClientes)) {
            return false;
        }
        AccesoClientes other = (AccesoClientes) object;
        if ((this.idacceso == null && other.idacceso != null) || (this.idacceso != null && !this.idacceso.equals(other.idacceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.AccesoClientes[ idacceso=" + idacceso + " ]";
    }
    
}
