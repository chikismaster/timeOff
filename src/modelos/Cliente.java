package modelos;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cliente extends javax.swing.JInternalFrame {
    
    private PreparedStatement ps;
    private conexion con;
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    
    public Cliente() {
        initComponents();
        ps = null;
        setTitutlos();
        listar();
    }
    
    private void listar(){
        tabla_clientes.setModel(getDatos());
    }
    
    private DefaultTableModel setTitutlos(){
        con = new conexion();
        DT.addColumn("IdCliente");
        DT.addColumn("Dni");
        DT.addColumn("Nombres");
        DT.addColumn("Direccion");
        DT.addColumn("Estado");
        DT.addColumn("adeudo");
        
        
        return DT;
    }
    
    private DefaultTableModel getDatos(){         
        String SQL_SELECT = "SELECT * FROM cliente";
        try {
            
            ps = con.getConnection().prepareStatement(SQL_SELECT);
            RS = ps.executeQuery();
            Object[] fila = new Object[6];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
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
    }
    
    public void reiniciar_id(){
        String reiniciarid = "ALTER TABLE cliente AUTO_INCREMENT = 1";
        try {
            ps = con.getConnection().prepareStatement(reiniciarid);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("no jalo el REINICIAR ID");
        }
    }
    public void limpia_crud(){
        txtDni.setText("");
        txtNom.setText("");
        txtDir.setText("");
        cboEstado.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setText("CELULAR:");

        jLabel2.setText("NOMBRES :");

        jLabel3.setText("DIRECCION :");

        jLabel4.setText("ESTADO :");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "1", "0" }));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNom)
                    .addComponent(txtDir)
                    .addComponent(cboEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDni))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_clientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_clientes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String dni = txtDni.getText().toString();
        String nom = txtNom.getText().toString();
        String dir = txtDir.getText().toString();
        String estado = cboEstado.getSelectedItem().toString();
        
        
        if ((dni.equals(""))||(nom.equals(""))||(dir.equals("")) || (estado.equals("SELECCIONAR"))) {
            JOptionPane.showMessageDialog(null, "Debe Ingresar todos los campos");
        }else{
            //String SQL_INSERT = "INSERT INTO cliente(Dni, Nombres, Direccion, Estado, adeudo) VALUES ('"+dni+"','"+nom+"','"+dir+"','"+estado+"','0')";
            String SQL_INSERT = "INSERT INTO cliente(Celular, Nombres, Direccion, Estado, adeudo) VALUES ('"+dni+"','"+nom+"','"+dir+"','"+estado+"','0')";
            try {
                ps = con.getConnection().prepareStatement(SQL_INSERT);
                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "registro guardado");
                }else{
                    JOptionPane.showMessageDialog(null, "NO GUARDO!!");
                }
                //metodos para que se refresque la tabla
                LimpiarTabla();
                tabla_clientes.setModel(getDatos());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR (usuario registrado cambie de usuario)");
            }
        
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        //seleccionar id de la tabla
        int fila = tabla_clientes.getSelectedRow();
        int id = Integer.parseInt(tabla_clientes.getValueAt(fila, 0).toString());
        //datos
        String dni = txtDni.getText().toString();
        String nom = txtNom.getText().toString();
        String dir = txtDir.getText().toString();
        String estado = cboEstado.getSelectedItem().toString();
        //consulta sql 
        String SQL_UPDATE = "UPDATE cliente SET Celular='"+dni+"', Nombres='"+nom+"', Direccion='"+dir+"', Estado='"+estado+"' WHERE IdCliente="+id+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
            //metodos para que se refresque la tabla
            LimpiarTabla();
            tabla_clientes.setModel(getDatos());
        } catch (SQLException e) {
            System.out.println("no sirve la actualizar");
        }
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //seleccionar id de la tabla
        int fila = tabla_clientes.getSelectedRow();
        int id = Integer.parseInt(tabla_clientes.getValueAt(fila, 0).toString());
        
        String SQL_DELETE = "DELETE FROM cliente WHERE IdCliente="+id+"";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_DELETE);
            ps.execute();
            //metodos para que se refresque la tabla
            LimpiarTabla();
            tabla_clientes.setModel(getDatos());
        } catch (SQLException ex) {
            System.out.println("no elimina vendedor");
        }
        limpia_crud();
        reiniciar_id();
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpia_crud();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        int fila = tabla_clientes.getSelectedRow();
        
        //int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
        String id = tabla_clientes.getValueAt(fila, 0).toString();
        String dni = tabla_clientes.getValueAt(fila,1).toString();
        String nom = tabla_clientes.getValueAt(fila, 2).toString();
        String dir = tabla_clientes.getValueAt(fila, 3).toString();
        int estado = Integer.parseInt(tabla_clientes.getValueAt(fila, 4).toString());
        
        
        txtDni.setText(dni);
        txtNom.setText(nom);
        txtDir.setText(dir);
        cboEstado.setSelectedIndex(estado);
    }//GEN-LAST:event_tabla_clientesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
