
package dev.muldev.gestiongym.backendgym.Modelos;


public class FormularioContacto {
    
    private String nombre;
    private String asunto;
    private int numeroTelefono;
    private String mensaje;

    public FormularioContacto() {
    }

    public FormularioContacto(String nombre, String asunto, int numeroTelefono, String mensaje) {
        this.nombre = nombre;
        this.asunto = asunto;
        this.numeroTelefono = numeroTelefono;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
    
}
