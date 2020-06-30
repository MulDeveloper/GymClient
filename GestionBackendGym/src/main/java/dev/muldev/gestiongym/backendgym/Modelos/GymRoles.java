/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "gym_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GymRoles.findAll", query = "SELECT g FROM GymRoles g"),
    @NamedQuery(name = "GymRoles.findById", query = "SELECT g FROM GymRoles g WHERE g.id = :id"),
    @NamedQuery(name = "GymRoles.findByAuthority", query = "SELECT g FROM GymRoles g WHERE g.authority = :authority")})
public class GymRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "authority")
    private String authority;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private AccesoClientes idcliente;

    public GymRoles() {
    }

    public GymRoles(String authority, AccesoClientes idcliente) {
        this.authority = authority;
        this.idcliente = idcliente;
    }
    
    

    public GymRoles(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public AccesoClientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(AccesoClientes idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GymRoles)) {
            return false;
        }
        GymRoles other = (GymRoles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.GymRoles[ id=" + id + " ]";
    }
    
}
