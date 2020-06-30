/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tarifas_gym")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarifasGym.findAll", query = "SELECT t FROM TarifasGym t"),
    @NamedQuery(name = "TarifasGym.findByIdtarifa", query = "SELECT t FROM TarifasGym t WHERE t.idtarifa = :idtarifa"),
    @NamedQuery(name = "TarifasGym.findByTotal", query = "SELECT t FROM TarifasGym t WHERE t.total = :total"),
    @NamedQuery(name = "TarifasGym.findByTotalIva", query = "SELECT t FROM TarifasGym t WHERE t.totalIva = :totalIva"),
    @NamedQuery(name = "TarifasGym.findByDescripcion", query = "SELECT t FROM TarifasGym t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TarifasGym.findByPermanencia", query = "SELECT t FROM TarifasGym t WHERE t.permanencia = :permanencia")})
public class TarifasGym implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtarifa")
    private Integer idtarifa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "total_iva")
    private BigDecimal totalIva;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "permanencia")
    private Boolean permanencia;

    public TarifasGym() {
    }

    public TarifasGym(Integer idtarifa) {
        this.idtarifa = idtarifa;
    }

    public Integer getIdtarifa() {
        return idtarifa;
    }

    public void setIdtarifa(Integer idtarifa) {
        this.idtarifa = idtarifa;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(Boolean permanencia) {
        this.permanencia = permanencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtarifa != null ? idtarifa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarifasGym)) {
            return false;
        }
        TarifasGym other = (TarifasGym) object;
        if ((this.idtarifa == null && other.idtarifa != null) || (this.idtarifa != null && !this.idtarifa.equals(other.idtarifa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.TarifasGym[ idtarifa=" + idtarifa + " ]";
    }

    
}
