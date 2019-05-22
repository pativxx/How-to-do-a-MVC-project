package vista.cliente;

import controlador.ClienteController;
import modelo.entidades.Cliente;
import modelo.ClienteModel;

/**
 *
 * @author Norberto Díaz-Díaz
 */
public class ClienteViewImpl extends javax.swing.JPanel implements ClienteView {

    //Atributos del MVC
    private ClienteModel model;
    
    private ClienteController controller;

    //Atributos propios de Swing
    private ClienteTableAndComboModel tableModel;
    private ClienteViewImplInternal panelCliente;

    /** Creates new form ClienteViewImpl */
    public ClienteViewImpl() {
        //tableModel = new ClienteTableAndComboModel();
        tableModel = ClienteTableAndComboModel.create();
        initComponents();

        this.panelCliente = new ClienteViewImplInternal(this);
        this.jPanelCliente.add(this.panelCliente);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jPanelCliente = new javax.swing.JPanel();

        jTableClientes.setModel(tableModel);
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableClientes);

        jPanelCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelCliente.setLayout(new javax.swing.BoxLayout(jPanelCliente, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        ClienteTableAndComboModel ctm = (ClienteTableAndComboModel) this.jTableClientes.getModel();
        Cliente cliente = ctm.getRow(this.jTableClientes.getSelectedRow());
        panelCliente.setCliente(cliente);
    }//GEN-LAST:event_jTableClientesMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables

    public void refresh() {
        tableModel.setClientes(getModel().obtenerClientes());//cambia el modelo de JTable(TableModel) por medio de una clase que hemos creado (ClientesTableModel)
    }

    protected void fireNuevoClienteGesture(String DNI, String nombre, String direccion) {
        getController().nuevoClienteGesture(DNI, nombre, direccion);
    }

    protected void fireActualizaClienteGesture(String DNI, String nombre, String direccion) {
        getController().actualizaClienteGesture(DNI, nombre, direccion);
    }

    protected void fireBorraClienteGesture(String DNI) {
        getController().borraClienteGesture(DNI);
    }

    public ClienteController getController() {
        return controller;
    }

    public void setController(ClienteController controller) {
        this.controller=controller;
    }

    public ClienteModel getModel() {
        return model;
    }

    public void setModel(ClienteModel model) {
        this.model=model;
    }

    public void dataModelChanged() {
        refresh();
    }

    public void display(){
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                setVisible(true);
                refresh();
            }
        });

    }
}
