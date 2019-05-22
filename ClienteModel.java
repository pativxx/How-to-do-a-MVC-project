package modelo;

import modelo.entidades.Cliente;
import controlador.ClienteController;
import java.util.List;

/**
 *
 * @author IS2: Norberto Díaz-Díaz
 */
public interface ClienteModel {
    //Enlaces con el controlador
    ClienteController getController();
    void setController(ClienteController controller);

    //Funciones que debe permitir el modelo
    void nuevoCliente(Cliente cliente);
    Cliente obtenerCliente(String nombre);
    void eliminarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    List<Cliente> obtenerClientes();
}
