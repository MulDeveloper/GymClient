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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "gym_classes")
@TypeDefs({
    @TypeDef(
        name = "int-array", 
        typeClass = IntArrayType.class
    )
})
@NamedQueries({
        @NamedQuery(name = "GymClassEntity.findAll", query = "SELECT c FROM GymClassEntity c"),
        @NamedQuery(name = "GymClassEntity.findByIdclase", query = "SELECT c FROM GymClassEntity c WHERE c.idclass = :idclass"),
        @NamedQuery(name = "GymClassEntity.findByFechaClase", query = "SELECT c FROM GymClassEntity c WHERE c.dateClass = :dateClass"),
        @NamedQuery(name = "GymClassEntity.findByHoraClase", query = "SELECT c FROM GymClassEntity c WHERE c.timeClass = :timeClass"),
        @NamedQuery(name = "GymClassEntity.findByDuracion", query = "SELECT c FROM GymClassEntity c WHERE c.duration = :duration"),
        @NamedQuery(name = "GymClassEntity.findByIntensidad", query = "SELECT c FROM GymClassEntity c WHERE c.intensity = :intensity"),
        @NamedQuery(name = "GymClassEntity.findByDescripcion", query = "SELECT c FROM GymClassEntity c WHERE c.description = :description"),
        @NamedQuery(name = "GymClassEntity.findByMonitor", query = "SELECT c FROM GymClassEntity c WHERE c.staff = :staff"),
        @NamedQuery(name = "GymClassEntity.findByListaClientes", query = "SELECT c FROM GymClassEntity c WHERE c.clients = :clients"),
    @NamedQuery(name = "GymClassEntity.findByFechaConcreta", query = "SELECT c FROM GymClassEntity c where c.dateClass between :desde and :hasta")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymClassEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idclass;
    private Date dateClass;
    private String timeClass;
    private String duration;
    private Integer intensity;
    private String description;
    private Integer staff;
    @Type(type = "int-array")
    @Column(
            name = "clients",
            columnDefinition = "integer[]"
    )
    private Serializable clients;


}
