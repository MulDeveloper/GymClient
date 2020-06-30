/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "clases_gym")
@XmlRootElement
@TypeDefs({
    @TypeDef(
        name = "int-array", 
        typeClass = IntArrayType.class
    )
})
@NamedQueries({
    @NamedQuery(name = "ClasesGym.findAll", query = "SELECT c FROM ClasesGym c"),
    @NamedQuery(name = "ClasesGym.findByIdclase", query = "SELECT c FROM ClasesGym c WHERE c.idclase = :idclase"),
    @NamedQuery(name = "ClasesGym.findByFechaClase", query = "SELECT c FROM ClasesGym c WHERE c.fechaClase = :fechaClase"),
    @NamedQuery(name = "ClasesGym.findByHoraClase", query = "SELECT c FROM ClasesGym c WHERE c.horaClase = :horaClase"),
    @NamedQuery(name = "ClasesGym.findByDuracion", query = "SELECT c FROM ClasesGym c WHERE c.duracion = :duracion"),
    @NamedQuery(name = "ClasesGym.findByIntensidad", query = "SELECT c FROM ClasesGym c WHERE c.intensidad = :intensidad"),
    @NamedQuery(name = "ClasesGym.findByDescripcion", query = "SELECT c FROM ClasesGym c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "ClasesGym.findByMonitor", query = "SELECT c FROM ClasesGym c WHERE c.monitor = :monitor"),
    @NamedQuery(name = "ClasesGym.findByListaClientes", query = "SELECT c FROM ClasesGym c WHERE c.listaClientes = :listaClientes"),
    @NamedQuery(name = "ClasesGym.findByFechaConcreta", query = "SELECT c FROM ClasesGym c where c.fechaClase between :desde and :hasta")
})
public class ClasesGym implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclase")
    private Integer idclase;
    @Column(name = "fecha_clase")
    @Temporal(TemporalType.DATE)
    private Date fechaClase;
    @Column(name = "hora_clase")
    private String horaClase;
    @Column(name = "duracion")
    private String duracion;
    @Column(name = "intensidad")
    private Integer intensidad;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "monitor")
    private Integer monitor;
    @Type( type = "int-array" )
    @Column(
        name = "lista_clientes", 
        columnDefinition = "integer[]"
    )
    private Serializable listaClientes;

    public ClasesGym() {
    }

    public ClasesGym(Integer idclase) {
        this.idclase = idclase;
    }

    public Integer getIdclase() {
        return idclase;
    }

    public void setIdclase(Integer idclase) {
        this.idclase = idclase;
    }

    public Date getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(Date fechaClase) {
        this.fechaClase = fechaClase;
    }

    public String getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(String horaClase) {
        this.horaClase = horaClase;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMonitor() {
        return monitor;
    }

    public void setMonitor(Integer monitor) {
        this.monitor = monitor;
    }

    public Serializable getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Serializable listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclase != null ? idclase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasesGym)) {
            return false;
        }
        ClasesGym other = (ClasesGym) object;
        if ((this.idclase == null && other.idclase != null) || (this.idclase != null && !this.idclase.equals(other.idclase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.gestiongym.backendgym.Modelos.ClasesGym[ idclase=" + idclase + " ]";
    }
    
}
