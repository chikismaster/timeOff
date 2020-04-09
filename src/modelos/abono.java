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
        
        String celular = txtCelularCliente.getText().toString();
        String idClie = id_cliente(celular);
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
    
     //Trae el id del cliente
        public String id_cliente(String celular){
        String SQL_select = "SELECT IdCliente FROM `cliente` WHERE Celular = '"+celular+"'";
        String lolo = "";
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra Celular de cliente");
        }
        
        return lolo;
    }
             //Trae el nombre del cliente
        public String nom_cliente(String celular){
        String SQL_select = "SELECT Nombres FROM `cliente` WHERE Celular = '"+celular+"'";
        String lolo = "";
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra Celular de cliente");
        }  
        return lolo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCelularCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        RealizarAbono = new javax.swing.JButton();
        VerAdeudo = new javax.swing.JButton();
        txtAdeudo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Abono");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Punto de Venta");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Abono");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tel: 312-135-55-79");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(256, Short.MAX_VALUE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(224, 224, 224)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(93, Short.MAX_VALUE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Cantidad a abonar");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("telefono del Cliente");

        txtCelularCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularClienteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre Cliente");

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 51, 255));
        txtNombreCliente.setCaretColor(new java.awt.Color(0, 51, 255));
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidad))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreCliente)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RealizarAbono.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        RealizarAbono.setText("Abonar");
        RealizarAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarAbonoActionPerformed(evt);
            }
        });

        VerAdeudo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        VerAdeudo.setText("Ver Adeudo");
        VerAdeudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerAdeudoActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Adeudo:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RealizarAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(VerAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RealizarAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VerAdeudo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtAdeudo, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCelularClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCelularClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularClienteActionPerformed

    private void RealizarAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RealizarAbonoActionPerformed
        // TODO add your handling code here:
        String celular = txtCelularCliente.getText().toString();
        String idClie=id_cliente(celular);
        double adeuda = monto(idClie);
        txtNombreCliente.setText(nom_cliente(celular));
        if (adeuda==0){
            JOptionPane.showMessageDialog(null, "QUE NO ADEUDA NADA MAZETON");
        }
        else{
        insertar_abono();
        JOptionPane.showMessageDialog(null, "Se genero Abono");
        dispose();
        }
        
        //JOptionPane.showMessageDialog(null, "si jalo el boton!!");
    }//GEN-LAST:event_RealizarAbonoActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtAdeudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdeudoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtAdeudoActionPerformed

    private void VerAdeudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerAdeudoActionPerformed
        // TODO add your handling code here:
        String celular = txtCelularCliente.getText().toString();
        String idClie=id_cliente(celular);
        double adeuda = monto(idClie);
        txtNombreCliente.setText(nom_cliente(celular));
        if (adeuda==0){
            txtAdeudo.setText("No adeuda nada");
        }
        else{
        txtAdeudo.setText("$"+adeuda);
        }
    }//GEN-LAST:event_VerAdeudoActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RealizarAbono;
    private javax.swing.JButton VerAdeudo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtAdeudo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCelularCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
