package sv.edu.unab.agenciavuelo.modelo.ejb;

import sv.edu.unab.agenciavuelo.modelo.entidades.Cliente;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClienteBeanLocal {

    List<Cliente> obtenerClientes();

    void guardarCliente(Cliente cliente);

    void eliminarCliente(Cliente cliente);

}
