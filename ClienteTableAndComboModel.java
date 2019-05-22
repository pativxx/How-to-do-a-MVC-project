package vista.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import modelo.entidades.Cliente;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public class ClienteTableAndComboModel implements TableModel, ComboBoxModel {


    private List<Cliente> clientes = new ArrayList<Cliente>();
    private List<TableModelListener> tableListeners = new ArrayList<TableModelListener>();
    private List<ListDataListener> dataListeners = new ArrayList<ListDataListener>();

    //Implementacion Singleton
    private static ClienteTableAndComboModel model=null;

    private ClienteTableAndComboModel(){}

    public static ClienteTableAndComboModel create(){
        if(model==null)
            model=new ClienteTableAndComboModel();
        return model;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireContentsChanged();
    }

    protected void fireContentsChanged() {
        fireContentsChangedTableModel();
        fireContentsChangedListData();
    }
    //--------------- MÉTODOS PROPIOS DE TableModel -------------------------

    public Cliente getRow(int indexRow) {
        return clientes.get(indexRow);
    }

    public int getRowCount() {
        return clientes.size();
    }

    public int getColumnCount() {
        return 3; //ya que tendremos tres valores a mostrar por cliente
    }

    public String getColumnName(int columnIndex) {
        String name = null;
        switch (columnIndex) {
            case 0:
                name = "DNI";
                break;
            case 1:
                name = "Nombre";
                break;
            case 2:
                name = "Direccion";
                break;
        }
        return name;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class; //Todos los valores de cliente son un String
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //No permito editar celdas
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        String value = null;
        switch (columnIndex) {
            case 0:
                value = cliente.getDNI();
                break;
            case 1:
                value = cliente.getNombre();
                break;
            case 2:
                value = cliente.getDireccion();
                break;
        }
        return value;

    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addTableModelListener(TableModelListener l) {
        tableListeners.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        tableListeners.remove(l);
    }

    protected void fireContentsChangedTableModel() {
        //TableModelEvent event = new TableModelEvent(this,0,getRowCount());
        TableModelEvent event = new TableModelEvent(this, 0, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        for (TableModelListener listener : tableListeners) {
            listener.tableChanged(event);
        }
    }
    //--------------- MÉTODOS PROPIOS DE ComboBoxModel -------------------------
    private Cliente clienteSelected = null;

    public void setSelectedItem(Object anItem) {
        if (anItem != null) {
            clienteSelected = (Cliente) anItem;
            fireContentsChangedListData();
        }
    }

    public Object getSelectedItem() {
        return clienteSelected;
    }

    public int getSize() {
        return clientes.size();
    }

    public Object getElementAt(int index) {
        return clientes.get(index);
    }

    public void addListDataListener(ListDataListener l) {
        dataListeners.add(l);
    }

    public void removeListDataListener(ListDataListener l) {
        dataListeners.remove(l);
    }

    protected void fireContentsChangedListData() {
        ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, clientes.size());
        for (ListDataListener l : dataListeners) {
            l.contentsChanged(e);
        }
    }
}
