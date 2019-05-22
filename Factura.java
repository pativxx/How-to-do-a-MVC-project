package modelo.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author IS2: Norberto Díaz-Díaz
 */
public interface Factura extends Serializable{
    
    String getIdentificador();
    Cliente getCliente();
    Double getImporte();
    
    void setIdentificador(String id);
    //void setCliente(Cliente cl);
    //void setImporte(Double im);
    
    void setFecha(Date fecha);
    Date getFecha();
}
