package modelos;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class venta extends javax.swing.JInternalFrame {
    
    private PreparedStatement ps;
    private conexion con;
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    

    public venta() {
        ps = null;
        initComponents();
        jTable1.setModel(getDatos2());
        numserie();
    }
    
    public void numserie(){
        int serie = 0;
        String SQL_sel = "select max(NumeroSerie) from ventas";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_sel);
            RS = ps.executeQuery();
            
            while (RS.next()) {                
                serie = RS.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("no se puso numSerie");
        }
        
        int res = serie+1;
        String se = String.valueOf(res);
        txtSerie.setText(se);
  
    }
    
    private DefaultTableModel setTitutlos(){
        con = new conexion();
        DT.addColumn("IdCliente");
        DT.addColumn("Dni");
        DT.addColumn("Nombres");
        DT.addColumn("Direccion");
        DT.addColumn("Estado");
        
        return DT;
    }
    private DefaultTableModel getDatos(String usu, String dni){
        String p = "";
        String o = "";
        
        if (!usu.equals("")) {
            p = "%";
        }
        if (!dni.equals("")) {
            o = "%";
        }
        
        String SQL_SELECT = "SELECT * FROM cliente WHERE Nombres LIKE '"+usu+p+"' OR Dni LIKE '"+dni+o+"'";
        try {
            setTitutlos();
            ps = con.getConnection().prepareStatement(SQL_SELECT);
            RS = ps.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                DT.addRow(fila);
            }
            System.out.println("si hizo el desmadre");
        } catch (SQLException e) {
            System.out.println("error de select");
        }
      return DT;
    }
    private DefaultTableModel setTitutlos2(){
        con = new conexion();
        DT.addColumn("IdVentas");
        DT.addColumn("IdCliente");
        DT.addColumn("IdVendedor");
        DT.addColumn("NumeroSerie");
        DT.addColumn("FechaVentas");
        DT.addColumn("Monto");
        DT.addColumn("Estado");
        return DT;
    }
    private DefaultTableModel remove(){
        con = new conexion();
        
        return DT;
    }
    private DefaultTableModel getDatos2(){
        
        String SQL_SELECT = "SELECT * FROM ventas";
        try {
            setTitutlos2();
            ps = con.getConnection().prepareStatement(SQL_SELECT);
            RS = ps.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
            System.out.println("si hizo el desmadre");
        } catch (SQLException e) {
            System.out.println("error de select");
        }
      return DT;
    }
    void LimpiarTabla() {
        for (int i = 0; i < DT.getRowCount(); i++) {
            DT.removeRow(i);
            i = i - 1;
        }
        DT.setColumnCount(0);
    }
    /*
    void LimpiarColumn() {
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.removeColumn(jTable1.getColumnModel().getColumn(i));
            i = i - 1;
        }
    }*/
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodProd = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        txtVendedor = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("venta");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTO DE VENTA \"TIME OFF\"");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Time Off");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tel: 312-135-55-79");

        jLabel4.setText("NRO SERIE:");

        txtSerie.setEditable(false);
        txtSerie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(133, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("COD: CLIENTE :");

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        txtCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCliente.setForeground(new java.awt.Color(0, 51, 255));
        txtCliente.setCaretColor(new java.awt.Color(0, 51, 255));
        txtCliente.setDisabledTextColor(new java.awt.Color(0, 51, 204));

        jLabel7.setText("CLIENTE :");

        jLabel8.setText("COD. PRODU  :");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setText("PRODUC");

        txtProducto.setEditable(false);
        txtProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtProducto.setForeground(new java.awt.Color(0, 51, 255));
        txtProducto.setCaretColor(new java.awt.Color(0, 51, 255));
        txtProducto.setDisabledTextColor(new java.awt.Color(0, 51, 204));

        jLabel10.setText("PRECIO");

        jLabel11.setText("STOCK :");

        txtStock.setEditable(false);
        txtStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStock.setForeground(new java.awt.Color(0, 51, 255));
        txtStock.setCaretColor(new java.awt.Color(0, 51, 255));
        txtStock.setDisabledTextColor(new java.awt.Color(0, 51, 204));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N
        jButton3.setText("Agregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setText("CANTIDAD :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        jLabel13.setText("VENDEDOR:");

        txtVendedor.setEditable(false);
        txtVendedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVendedor.setForeground(new java.awt.Color(0, 51, 255));
        txtVendedor.setCaretColor(new java.awt.Color(0, 51, 255));
        txtVendedor.setDisabledTextColor(new java.awt.Color(0, 51, 204));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 51, 255));
        txtFecha.setCaretColor(new java.awt.Color(0, 51, 255));
        txtFecha.setDisabledTextColor(new java.awt.Color(0, 51, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrecio)
                    .addComponent(txtCodCliente)
                    .addComponent(txtCodProd)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFecha))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(15, 15, 15)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(txtStock)
                    .addComponent(txtProducto)
                    .addComponent(txtCliente))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jButton2)
                    .addComponent(jLabel9)
                    .addComponent(txtProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("TOTAL A PAGAR :");

        txtTotalPagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnGenerar.setText("GENERAR VENTA");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnGenerar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPagar)
                    .addComponent(jLabel14)
                    .addComponent(btnGenerar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        LimpiarTabla();
        String cliente = txtCliente.getText().toString();
        String dni = txtCodCliente.getText().toString();
        jTable1.setModel(getDatos(cliente, dni));
        //txtCodCliente.setText("");
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LimpiarTabla();
        String cod_pro = txtCodProd.getText().toString();
        
        String SQL_select = "SELECT * FROM `producto` WHERE Codigo_product = '"+cod_pro+"'";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            
            while (RS.next()) {                
                txtProducto.setText(RS.getString(2));
                txtPrecio.setText(RS.getString(4));
                txtStock.setText(RS.getString(5));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Producto No registrado");
        }
        
        txtVendedor.setText(login.a);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LimpiarTabla();
        jTable1.setModel(getDatos2());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        
        int numcol = jTable1.getColumnCount();
        
        System.out.println(numcol);
        
        if (numcol == 5) {
            String id = jTable1.getValueAt(fila, 0).toString();
            String dni = jTable1.getValueAt(fila,1).toString();
            String nom = jTable1.getValueAt(fila, 2).toString();
            String dir = jTable1.getValueAt(fila, 3).toString();
            int estado = Integer.parseInt(jTable1.getValueAt(fila, 4).toString());
            
            txtCodCliente.setText(dni);
            txtCliente.setText(nom);
            
        }else if(numcol == 7){
            /*
            String idvent = jTable1.getValueAt(fila, 0).toString();
            String idcli = jTable1.getValueAt(fila, 1).toString();
            String idvend = jTable1.getValueAt(fila, 2).toString();
            String numser = jTable1.getValueAt(fila, 3).toString();
            String fecvent = jTable1.getValueAt(fila, 4).toString();
            String monto = jTable1.getValueAt(fila, 5).toString();
            String estad = jTable1.getValueAt(fila, 6).toString();
            */
            
        }
        //jTable1.setModel(getDatos2());
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodProd;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtSerie;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
