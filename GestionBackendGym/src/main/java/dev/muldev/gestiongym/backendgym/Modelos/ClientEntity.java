/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.gestiongym.backendgym.Modelos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ClientesGym.findAll", query = "SELECT c FROM ClientEntity c"),
        @NamedQuery(name = "ClientesGym.findByIdcliente", query = "SELECT c FROM ClientEntity c WHERE c.idcliente = :idcliente"),
        @NamedQuery(name = "ClientesGym.findByNombreCliente", query = "SELECT c FROM ClientEntity c WHERE c.nombreCliente = :nombreCliente"),
        @NamedQuery(name = "ClientesGym.findByApellidoCliente", query = "SELECT c FROM ClientEntity c WHERE c.apellidoCliente = :apellidoCliente"),
        @NamedQuery(name = "ClientesGym.findByEmailCliente", query = "SELECT c FROM ClientEntity c WHERE c.emailCliente = :emailCliente"),
        @NamedQuery(name = "ClientesGym.findByNifCliente", query = "SELECT c FROM ClientEntity c WHERE c.nifCliente = :nifCliente"),
        @NamedQuery(name = "ClientesGym.findByTelCliente", query = "SELECT c FROM ClientEntity c WHERE c.telCliente = :telCliente"),
        @NamedQuery(name = "ClientesGym.findByDireccionCliente", query = "SELECT c FROM ClientEntity c WHERE c.direccionCliente = :direccionCliente"),
        @NamedQuery(name = "ClientesGym.findByIdMatricula", query = "SELECT c FROM ClientEntity c WHERE c.idMatricula = :idMatricula"),
        @NamedQuery(name = "ClientesGym.findByFechaNacimiento", query = "SELECT c FROM ClientEntity c WHERE c.fechaNacimiento = :fechaNacimiento"),
        @NamedQuery(name = "ClientesGym.findByInput", query = "SELECT c FROM ClientEntity c WHERE lower(c.apellidoCliente) like :input or lower(c.nombreCliente) like :input"),
        @NamedQuery(name = "ClientesGym.countByAge", query = "SELECT count (*) FROM ClientEntity c WHERE extract(year from age(c.fechaNacimiento)) between :minage and :maxage")
})
public class ClientEntity implements Serializable {

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
    private Date fechaNacimiento;


}
