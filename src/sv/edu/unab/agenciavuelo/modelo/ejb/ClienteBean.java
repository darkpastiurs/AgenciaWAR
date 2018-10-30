package sv.edu.unab.agenciavuelo.modelo.ejb;

import sv.edu.unab.agenciavuelo.modelo.entidades.Cliente;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

@Stateless
public class ClienteBean implements ClienteBeanLocal {
    
    private static final Logger LOG = Logger.getLogger(ClienteBean.class.getName());
    
    @PersistenceContext(unitName = "AgenciaPU")
    private EntityManager em;
    
    @Override
    public List<Cliente> obtenerClientes() {
        LOG.log(INFO, "[ClienteBean][obtenerClientes]");
        List<Cliente> resultado = null;
        try {
            Query query = em.createQuery("SELECT c FROM Cliente c");
            resultado = query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "[ClienteBean][obtenerClientes][Excepcion] -> ", e);
        }
        return resultado;
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void guardarCliente(Cliente cliente) {
        LOG.log(INFO, "[ClienteBean][guardarCliente] -> ", new Object[]{cliente});
        try {
            Cliente clienteEntity;
            if(cliente.getId() != null){
                clienteEntity = em.find(Cliente.class, cliente.getId());
            } else {
                clienteEntity = new Cliente();
            }
            clienteEntity.setPasaporte(cliente.getPasaporte());
            clienteEntity.setVisa(cliente.getVisa());
            clienteEntity.setDatosPersonales(cliente.getDatosPersonales());
            if(cliente.getId() != null){
                em.merge(clienteEntity);
            } else {
                em.persist(clienteEntity);
            }
            em.flush();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "[ClienteBean][guardarCliente][Excepcion] -> ", e);
        }
    }

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void eliminarCliente(Cliente cliente) {
        LOG.log(INFO, "[ClienteBean][eliminarCliente] -> ", new Object[]{cliente});
        try {
            Cliente clienteEntity = em.find(Cliente.class, cliente.getId());
            em.remove(em.merge(clienteEntity));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "[ClienteBean][eliminarCliente][Excepcion] -> ", e);
        }
    }
}
