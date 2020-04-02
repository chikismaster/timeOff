/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author marca
 */
public class corteFinal extends javax.swing.JInternalFrame {
//variables globales
    public static String nom_gfe;
    //para conexion
    private PreparedStatement ps;
    private conexion con = new conexion();
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    /**
     * Creates new form corteFinal
     */
    public corteFinal() {
        ps = null;
        initComponents();
        nom_gfe = login.a;
        System.out.println(fechahoy());
        //para obtener vendedor usuario
        txtVendedor.setText(login.a);
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
    
    public void cant_inicial(){
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT Cant_inicial FROM corte WHERE Fecha_inicio = '"+Fecha+"' and Estado = '1'";
        double lolo =0.0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }   
        String inicial = String.valueOf(lolo);
        txtInicial.setText(inicial);
    }
    
    public void pago_efectivo(){
        int idv=id_ven();
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT SUM(Monto) FROM ventas WHERE FechaVentas = '"+Fecha+"' and tipo_pago = 'e' and IdVendedor="+idv+" ";
        double lolo =0.0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }   
        String efect = String.valueOf(lolo);
        txtEfectivo.setText(efect);
    }
    
    public void pago_tarjeta(){
        int idv=id_ven();
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT SUM(Monto) FROM ventas WHERE FechaVentas = '"+Fecha+"' and tipo_pago = 't' and IdVendedor="+idv+" ";
        double lolo =0.0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }   
        String tarj = String.valueOf(lolo);
        txtTarjeta.setText(tarj);
    }
    
    public void pago_en_abono(){
        int idv= id_ven();
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT SUM(Monto) FROM ventas WHERE FechaVentas = '"+Fecha+"' and tipo_pago = 'a' and IdVendedor="+idv+" ";
        double lolo =0.0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }   
        String abo = String.valueOf(lolo);
        txtAbono.setText(abo);
    }
    
    
        //Verifica si aun no se a echo corte 
     public int estado_corte(){
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT Estado FROM corte WHERE Fecha_inicio = '"+Fecha+"' and Estado = '1'";
        int lolo = 0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }
        return lolo;
    }
     
     //Actualiza Estado de Corte 
     public void actualizar_estado(int id,String esta){
        String SQL_UPDATE = "UPDATE `corte` SET Estado='"+esta+"' WHERE IdCorte ="+id+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se actualizo estado del corte");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No sirvio la actualizada en estado");
        }
    }
     
     //devueleve id del corte
       public int id_corte(){
        String Fecha = txtFecha.getText().toString();
        String SQL_select = "SELECT IdCorte FROM corte WHERE Fecha_inicio = '"+Fecha+"' and Estado = '1'";
        int lolo = 0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }
        return lolo;
    }
    //Trae el id del vendedor
        public int id_ven(){
        String userven = txtVendedor.getText().toString();
        String SQL_select = "SELECT * FROM `vendedor` WHERE User = '"+userven+"'";
        int lolo = 0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no encuentra ID vendedor");
        }
        
        return lolo;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel11 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        txtTarjeta = new javax.swing.JTextField();
        txtAbono = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        Generar = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtInicial = new javax.swing.JTextField();
        Correcto = new javax.swing.JButton();
        Faltp = new javax.swing.JButton();
        Sobro = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Realizar Corte");

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

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVendedor.setForeground(new java.awt.Color(0, 51, 255));
        txtVendedor.setCaretColor(new java.awt.Color(0, 51, 255));
        txtVendedor.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorActionPerformed(evt);
            }
        });

        jLabel16.setText("Usuario");

        jLabel15.setText("Pago Efectivo");

        jLabel17.setText("Pago Tarjeta");

        jLabel18.setText("Pago en Abono");

        jLabel19.setText("Total Venta");

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtEfectivo.setForeground(new java.awt.Color(0, 51, 255));
        txtEfectivo.setCaretColor(new java.awt.Color(0, 51, 255));
        txtEfectivo.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
            }
        });

        txtTarjeta.setEditable(false);
        txtTarjeta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTarjeta.setForeground(new java.awt.Color(0, 51, 255));
        txtTarjeta.setCaretColor(new java.awt.Color(0, 51, 255));
        txtTarjeta.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarjetaActionPerformed(evt);
            }
        });

        txtAbono.setEditable(false);
        txtAbono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAbono.setForeground(new java.awt.Color(0, 51, 255));
        txtAbono.setCaretColor(new java.awt.Color(0, 51, 255));
        txtAbono.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbonoActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 51, 255));
        txtTotal.setCaretColor(new java.awt.Color(0, 51, 255));
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        Generar.setText("Generar Corte");
        Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarActionPerformed(evt);
            }
        });

        jLabel20.setText("Cantidad Inicial");

        txtInicial.setEditable(false);
        txtInicial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtInicial.setForeground(new java.awt.Color(0, 51, 255));
        txtInicial.setCaretColor(new java.awt.Color(0, 51, 255));
        txtInicial.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInicialActionPerformed(evt);
            }
        });

        Correcto.setText("Completo");
        Correcto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorrectoActionPerformed(evt);
            }
        });

        Faltp.setText("Falto");
        Faltp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FaltpActionPerformed(evt);
            }
        });

        Sobro.setText("Sobro");
        Sobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Correcto)
                                    .addComponent(Generar)
                                    .addComponent(Faltp)
                                    .addComponent(Sobro))))
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(txtInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Correcto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Faltp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addComponent(Sobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorActionPerformed

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void txtTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaActionPerformed

    private void txtAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbonoActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarActionPerformed
        int est = estado_corte();
        if(est==1){
            cant_inicial();
            pago_efectivo();
            pago_tarjeta();
            pago_en_abono();
            double ini = Double.parseDouble(txtInicial.getText().toString());
            double efe = Double.parseDouble(txtEfectivo.getText().toString());
            double tar = Double.parseDouble(txtTarjeta.getText().toString());
            double abono = Double.parseDouble(txtAbono.getText().toString());
            double tot =ini+efe+tar+abono;
            String t = String.valueOf(tot);
            txtTotal.setText(t);
        }
        
        else{
            JOptionPane.showMessageDialog(null, "Abra primero fondo inicial");
            dispose();    
        }
        
        //JOptionPane.showMessageDialog(null, "si jalo el boton!!");
    }//GEN-LAST:event_GenerarActionPerformed

    private void txtInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInicialActionPerformed

    private void CorrectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorrectoActionPerformed
        // TODO add your handling code here:
        String estado="Correcto";
        int id=id_corte();
        System.out.println(id);
        actualizar_estado(id,estado);
        dispose();
    }//GEN-LAST:event_CorrectoActionPerformed

    private void FaltpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FaltpActionPerformed
        // TODO add your handling code here:
        String estado="Falto";
        int id=id_corte();
        actualizar_estado(id,estado);
        dispose();
    }//GEN-LAST:event_FaltpActionPerformed

    private void SobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobroActionPerformed
        // TODO add your handling code here:
        String estado="Sobro";
        int id=id_corte();
        actualizar_estado(id,estado);
        dispose();
    }//GEN-LAST:event_SobroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Correcto;
    private javax.swing.JButton Faltp;
    private javax.swing.JButton Generar;
    private javax.swing.JButton Sobro;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtInicial;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
