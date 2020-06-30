/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import java.io.Serializable;
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
@Table(name = "matriculas_gym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MatriculasGym.findAll", query = "SELECT m FROM MatriculasGym m"),
    @NamedQuery(name = "MatriculasGym.findByIdmatricula", query = "SELECT m FROM MatriculasGym m WHERE m.idmatricula = :idmatricula"),
    @NamedQuery(name = "MatriculasGym.findByIdcliente", query = "SELECT m FROM MatriculasGym m WHERE m.idcliente = :idcliente"),
    @NamedQuery(name = "MatriculasGym.findByIdtarifa", query = "SELECT m FROM MatriculasGym m WHERE m.idtarifa = :idtarifa"),
    @NamedQuery(name = "MatriculasGym.findByFechaAlta", query = "SELECT m FROM MatriculasGym m WHERE m.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "MatriculasGym.findByEstado", query = "SELECT m FROM MatriculasGym m WHERE m.estado = :estado")})
public class MatriculasGym implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmatricula")
    private Integer idmatricula;
    @Column(name = "idcliente")
    private Integer idcliente;
    @Column(name = "idtarifa")
    private Integer idtarifa;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @Column(name = "estado")
    private Boolean estado;

    public MatriculasGym() {
    }

    public MatriculasGym(Integer idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Integer getIdmatricula() {
        return idmatricula;
    }

    public void setIdmatricula(Integer idmatricula) {
        this.idmatricula = idmatricula;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(Integer idtarifa) {
        this.idtarifa = idtarifa;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmatricula != null ? idmatricula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculasGym)) {
            return false;
        }
        MatriculasGym other = (MatriculasGym) object;
        if ((this.idmatricula == null && other.idmatricula != null) || (this.idmatricula != null && !this.idmatricula.equals(other.idmatricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.MatriculasGym[ idmatricula=" + idmatricula + " ]";
    }
    
}
