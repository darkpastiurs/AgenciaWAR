package sv.edu.unab.agenciavuelo.controladores;

import sv.edu.unab.agenciavuelo.modelo.ejb.ClienteBeanLocal;
import sv.edu.unab.agenciavuelo.modelo.entidades.Cliente;
import sv.edu.unab.agenciavuelo.modelo.entidades.Persona;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class IndexCtrl implements Serializable {
    private static final long serialVersionUID = 6360261893943165567L;
    private static final Logger LOG = Logger.getLogger(IndexCtrl.class.getName());

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Character genero;
    private String dui;
    private String nit;
    private String telefono;
    private Date fechaNacimiento;
    private String direccion;
    private String email;
    private String pasaporte;
    private String visa;

    @EJB
    private ClienteBeanLocal clienteBean;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String guardar() {
        LOG.log(Level.INFO, "[IndexCtrl][guardar]");
        try {
            Cliente cliente = new Cliente();
            Persona datosPersonales = new Persona();
            datosPersonales.setNombre(nombre);
            datosPersonales.setApellidoPaterno(apellidoPaterno);
            datosPersonales.setApellidoMaterno(apellidoMaterno);
            datosPersonales.setGenero(genero);
            datosPersonales.setDui(dui);
            datosPersonales.setNit(nit);
            datosPersonales.setTelefono(telefono);
            datosPersonales.setFechaNacimiento(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            datosPersonales.setDireccion(direccion);
            datosPersonales.setEmail(email);
            cliente.setDatosPersonales(datosPersonales);
            cliente.setPasaporte(pasaporte);
            cliente.setVisa(visa);
            clienteBean.guardarCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index.xhtml?faces-redirect=true";
    }
}
