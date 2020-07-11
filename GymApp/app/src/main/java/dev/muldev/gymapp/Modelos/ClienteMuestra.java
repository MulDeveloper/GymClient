package dev.muldev.gymapp.Modelos;

public class ClienteMuestra {

    private long idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String nifCliente;
    private long telCliente;
    private String direccionCliente;
    private long idMatricula;
    private String fechaNacimiento;

    public ClienteMuestra(long idCliente, String nombreCliente, String apellidoCliente, String emailCliente, String nifCliente, long telCliente, String direccionCliente, long idMatricula, String fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.emailCliente = emailCliente;
        this.nifCliente = nifCliente;
        this.telCliente = telCliente;
        this.direccionCliente = direccionCliente;
        this.idMatricula = idMatricula;
        this.fechaNacimiento = fechaNacimiento;
    }

    public ClienteMuestra() {
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
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

    public long getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(long telCliente) {
        this.telCliente = telCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public long getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(long idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
