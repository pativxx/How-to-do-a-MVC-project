package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.FacturaModel;
import modelo.entidades.Cliente;
import modelo.entidades.Factura;
import modelo.entidades.FacturaImpl;
import vista.factura.FacturaView;

/**
 *
 * @author IS2: Norberto Díaz-Díaz, Roberto Ruiz
 */
public class FacturaControllerImpl implements FacturaController {

    private FacturaModel model;

    private List<FacturaView> views;

    public FacturaControllerImpl() {
        this.views = new ArrayList<FacturaView>();
    }

    @Override
    public void setup(FacturaModel model, List<FacturaView> views) {
        this.model = model;
        model.setController(this);
        addViews(views);
    }

    @Override
    public void start() {
        for (FacturaView f : this.views) {
            f.display();
        }
    }

    private void addViews(List<FacturaView> views) {
        for (FacturaView f : views) {
            this.addView(f);
        }
    }

    @Override
    public void addView(FacturaView view) {
        this.views.add(view);
        view.setController(this);
        view.setModel(this.model);
    }

    @Override
    public void removeView(FacturaView view) {
        this.views.remove(view);
    }

    @Override
    public FacturaModel getModel() {
        return this.model;
    }

    @Override
    public void setModel(FacturaModel model) {
        this.model = model;
    }

    @Override
    public void nuevaFacturaGesture(String id, Cliente cliente, String importe, String fechaEnString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null; // Variable que almacenará la fecha
        // fechaEnString es de tipo String y contiene la fecha a convertir
        if (fechaEnString.length() > 0) {
            try {
                fecha = formatter.parse(fechaEnString);
            } catch (ParseException ex) {
                // Tratamiento de excepción de conversión de String a Date
            }
        }

        Factura factura = new FacturaImpl(id, cliente, new Double((String) importe), fecha);
        this.model.nuevaFactura(factura);
    }

    @Override
    public void borraFacturaGesture(String id) {
        Factura factura = new FacturaImpl(id);
        this.model.eliminarFactura(factura);
    }

    @Override
    public void actualizaFacturaGesture(String id, Cliente cliente, String importe, String fechaEnString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null; // Variable que almacenará la fecha
        // fechaEnString es de tipo String y contiene la fecha a convertir
        if (fechaEnString.length() > 0) {
            try {
                fecha = formatter.parse(fechaEnString);
            } catch (ParseException ex) {
                // Tratamiento de excepción de conversión de String a Date
            }
        }
        Factura factura = new FacturaImpl(id, cliente, new Double((String) importe), fecha);
        this.model.actualizarFactura(factura);
    }

    @Override
    public void fireDataModelChanged() {
        for (FacturaView f : this.views) {
            f.display();
        }
    }

}
