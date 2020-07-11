package dev.muldev.gymapp.Modelos;

import java.util.Date;

public class Clase {
    private int id;
    private Date fechaClase;
    private String horaClase;
    private String duracion;
    private int intensidad;
    private String descripcion;
    private int monitor;
    private int sizeClientes;

    public Clase() {
    }

    public Clase(int id, String horaClase, String duracion, String descripcion, int sizeClientes) {
        this.id = id;
        this.horaClase = horaClase;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.sizeClientes = sizeClientes;
    }

    public Clase(int id, Date fechaClase, String horaClase, String duracion, int intensidad, String descripcion, int monitor, int sizeClientes) {
        this.id = id;
        this.fechaClase = fechaClase;
        this.horaClase = horaClase;
        this.duracion = duracion;
        this.intensidad = intensidad;
        this.descripcion = descripcion;
        this.monitor = monitor;
        this.sizeClientes = sizeClientes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonitor() {
        return monitor;
    }

    public void setMonitor(int monitor) {
        this.monitor = monitor;
    }

    public int getSizeClientes() {
        return sizeClientes;
    }

    public void setSizeClientes(int sizeClientes) {
        this.sizeClientes = sizeClientes;
    }
}
