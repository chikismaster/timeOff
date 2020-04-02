package modelos;
import conexion.conexion;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import imprimir.imprimir2;

/**
 *
 * @author Gabriel
 */
public class abono extends javax.swing.JInternalFrame {
    private PreparedStatement ps;
    private conexion con = new conexion();
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    /**
     * Creates new form abono
     */
    public abono() {
        ps=null;
        initComponents();
        System.out.println(fechahoy());
        
    }
    
 //funcion que devuelve la fecha de hoy en string
    public String fechahoy(){
        Date fecha = new Date();
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = new GregorianCalendar();
        
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c2.get(Calendar.MONTH));
        int mm = Integer.parseInt(mes);
        int nmes = mm +1;
        String memes = String.valueOf(nmes);
        if (nmes < 10) {
            memes = "0"+memes;
        }
        String anio = Integer.toString(c1.get(Calendar.YEAR));
        
        String fechoy = anio+"-"+memes+"-"+dia;
        
        txtFecha.setText(fechoy);
        return fechoy;
    }
      
    //insertar en tabla abono
    public void insertar_abono(){
        
        String idClie = txtCodigoCliente.getText().toString();
        //String abono = txtCantidad.getText().toString();
        String abono = txtCantidad.getText().toString();
        String fec = fechahoy();
        String SQL_insert = "INSERT INTO `detalle_abono`(`idCliente`, `cant_abono`, `fecha_abono`) VALUES ('"+idClie+"','"+abono+"','"+fec+"')";       
        try {
            ps = con.getConnection().prepareStatement(SQL_insert);
            ps.execute();
            actualizaradeudo(idClie,abono);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no guardo en abono");
        }     
    }
    //actualiza el monto de adeudo
    public void actualizaradeudo(String idCliente, String abono){
        double adeuda = monto(idCliente);
        double abo = Double.parseDouble(abono);
        
        double tot= adeuda-abo;
        System.out.println(tot);
        String SQL_UPDATE = "UPDATE `cliente` SET adeudo="+tot+" WHERE IdCliente = "+idCliente+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
            if(tot==0.0){
                actualizaestado(idCliente);
            }
        } catch (SQLException e) {
            System.out.println("no sirve la actualizar");
        }
    }
    //devuelve el monto que adeuda 
    public double monto(String idClie){
        String SQL_select = "SELECT adeudo FROM `cliente` WHERE idCliente = '"+idClie+"'";
        double lolo = 0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        }
        return lolo;
    }
    
    
    //actualiza estado del cliente
    public void actualizaestado(String idClie){
        String SQL_UPDATE = "UPDATE `cliente` SET Estado='0' WHERE IdCliente = "+idClie+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("No se actualizo el Estado");
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        RealizarAbono = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        txtAdeudo = new javax.swing.JTextField();
        VerAdeudo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Abono");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Punto de Venta");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Abono");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tel: 312-135-55-79");

        jLabel14.setText("Cantidad a abonar");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel15.setText("Codigo Cliente");

        txtCodigoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoClienteActionPerformed(evt);
            }
        });

        RealizarAbono.setText("Abonar");
        RealizarAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarAbonoActionPerformed(evt);
            }
        });

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 51, 255));
        txtFecha.setCaretColor(new java.awt.Color(0, 51, 255));
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtAdeudo.setEditable(false);
        txtAdeudo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAdeudo.setForeground(new java.awt.Color(0, 51, 255));
        txtAdeudo.setCaretColor(new java.awt.Color(0, 51, 255));
        txtAdeudo.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtAdeudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdeudoActionPerformed(evt);
            }
        });

        VerAdeudo.setText("Ver Adeudo");
        VerAdeudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerAdeudoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37))))
                .addGap(118, 118, 118))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(30, 30, 30)
                                .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(txtAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(RealizarAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(VerAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(23, 23, 23)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RealizarAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VerAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoClienteActionPerformed

    private void RealizarAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarAbonoActionPerformed
        // TODO add your handling code here:
        insertar_abono();
        JOptionPane.showMessageDialog(null, "Se genero Abono");
        dispose();
        //JOptionPane.showMessageDialog(null, "si jalo el boton!!");
    }//GEN-LAST:event_RealizarAbonoActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtAdeudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdeudoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtAdeudoActionPerformed

    private void VerAdeudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerAdeudoActionPerformed
        // TODO add your handling code here:
        String idClie = txtCodigoCliente.getText().toString();
        double adeuda = monto(idClie);
        if (adeuda==0){
            txtAdeudo.setText("No adeuda");
        }
        else{
        txtAdeudo.setText("$"+adeuda);
        }
    }//GEN-LAST:event_VerAdeudoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RealizarAbono;
    private javax.swing.JButton VerAdeudo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JTextField txtAdeudo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
