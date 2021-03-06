
package modelos;

import conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;


public class vendedor extends javax.swing.JInternalFrame {

    private PreparedStatement ps;
    private conexion con = new conexion();
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    private String SQL_select = "select * from vendedor";
    
    //*************************varriables globales******************************
    //var global para almacenar el id del vendedor
    public static String idvend = "";
    
    public vendedor() {
        initComponents();
        ps = null;
        listar();
        //esta clase sirve para ver si se cerro desconectar de db
        addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println("se cerro vendedor");
                con.desconectar();
                // do something  
            }
        });
    }
    //*******************TABLA VENDEDOR****************************************
    private void listar(){
        jTable1.setModel(getDatos());
    }
    //columnas tabla vendedor
    private DefaultTableModel setTitutlos(){
        DT.addColumn("IdVendedor");
        DT.addColumn("Contra");
        DT.addColumn("Nombres");
        DT.addColumn("Telefono");
        DT.addColumn("Estado");
        DT.addColumn("User");
        
        return DT;
    }
    //mostrar en tabla
    private DefaultTableModel getDatos(){  
        try {
            setTitutlos();
            ps = con.getConnection().prepareStatement(SQL_select);
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
            
        } catch (SQLException e) {
            System.out.println("error de select");
        }
      return DT;
    }
    private DefaultTableModel getDatos2(){ 
        try {
            //setTitutlos();
            ps = con.getConnection().prepareStatement(SQL_select);
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
    
    public void eliminar_t(){
        DefaultTableModel tb = (DefaultTableModel) jTable1.getModel();
    }
    
    public void limpia_crud(){
        txtDni.setText("");
        txtNombres.setText("");
        txtTelefono.setText("");
        comboEstado.setSelectedIndex(0);
        txtusuario.setText("");
        idvend="";
    }
    
    public void reiniciar_id(){
        String reiniciarid = "ALTER TABLE vendedor AUTO_INCREMENT = 1";
        try {
            ps = con.getConnection().prepareStatement(reiniciarid);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("no jalo el REINICIAR ID");
        }
    }
    
    //funcion para hacer el vendedor inactivo
    public void inactivo_vendedor(){
        //consulta sql
        String SQL_SELECT ="UPDATE vendedor v SET v.Estado = 0 WHERE v.IdVendedor = "+idvend+"";
        //ejecutar consulta
        try {
            ps = con.getConnection().prepareStatement(SQL_SELECT);
            ps.execute();
            //metodos para que se refresque la tabla
            LimpiarTabla();
            jTable1.setModel(getDatos2());
        } catch (SQLException e) {
            System.out.println("no sirve la actualizar");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtDni = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtNombres = new javax.swing.JTextField();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Vendedor");

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_1.png"))); // NOI18N
        btnGuardar.setText("Agregar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "0" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("NOMBRES");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("ESTADO");

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/update1.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new5.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CONTRASEÑA");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("CELULAR");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("USUARIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboEstado, 0, 349, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres)
                            .addComponent(txtTelefono)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtusuario)
                            .addComponent(txtDni))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.MatteBorder(null));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "idvendedor", "dni", "nombre", "telefono", "estado", "usuario"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //se guarda var para usar de los textfield
        String dn = txtDni.getText().toString();
        String nom = txtNombres.getText().toString();
        String tel = txtTelefono.getText().toString();
        String estado = comboEstado.getSelectedItem().toString();
        String usu = txtusuario.getText().toString();
        //imprimimos usario
        System.out.println(usu);
        System.out.println("estado-->"+estado);
        //validamos que ingresen todos los campos
        if ((dn.equals("")) || (nom.equals("")) || (tel.equals("")) || (estado.equals("")) || (usu.equals("")) || estado.equals("Seleccionar")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar todos los campos");
        }else{
            //una vez validado se hace el insert
            String SQL_INSERT = "INSERT INTO vendedor (Dni,Nombres,Telefono,Estado,User) values('"+dn+"','"+nom+"','"+tel+"','"+estado+"','"+usu+"')";
            try {
                ps = con.getConnection().prepareStatement(SQL_INSERT);
                int res = ps.executeUpdate();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "registro guardado");
                    limpia_crud();
                }else{
                    JOptionPane.showMessageDialog(null, "NO GUARDO!!");
                }
                //metodos para que se refresque la tabla
                LimpiarTabla();
                jTable1.setModel(getDatos2());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR (usuario registrado cambie de usuario)");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //agregar datos
        String dni = txtDni.getText().toString();
        String nom = txtNombres.getText().toString();
        String tel = txtTelefono.getText().toString();
        String estado = comboEstado.getSelectedItem().toString();
        String usu = txtusuario.getText().toString();
        if ((dni.equals("")) || (nom.equals("")) || (tel.equals("")) || (estado.equals("")) || (usu.equals("")) || estado.equals("Seleccionar")) {
            JOptionPane.showMessageDialog(this, "Debe Ingresar todos los campos");
        }else{
            //consulta sql
            String SQL_UPDATE ="UPDATE vendedor SET Dni='"+dni+"', Nombres='"+nom+"', Telefono = '"+tel+"', Estado = '"+estado+"', User = '"+usu+"' WHERE IdVendedor = "+idvend+"";
            //ejecutar consulta
            try {
                ps = con.getConnection().prepareStatement(SQL_UPDATE);
                ps.execute();
                //metodos para que se refresque la tabla
                LimpiarTabla();
                jTable1.setModel(getDatos2());
            } catch (SQLException e) {
                System.out.println("no sirve la actualizar");
            }
        }
        limpia_crud();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idvend.equals("")) {
            JOptionPane.showMessageDialog(null, "seleccione un vendedor");
        }else{
            //agregar datos
            String dni = txtDni.getText().toString();

            String SQL_DELETE = "DELETE FROM vendedor WHERE IdVendedor ="+idvend+"";

            try {
                ps = con.getConnection().prepareStatement(SQL_DELETE);
                ps.execute();
                //metodos para que se refresque la tabla
                LimpiarTabla();
                jTable1.setModel(getDatos2());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "este vendedor tiene ventas registradas (que sea inactivo)");
                int r = JOptionPane.showConfirmDialog(this, "¿quieres hacer el vendedor inactivo?");
                if (r == 0) {
                    inactivo_vendedor();
                }else{
                    JOptionPane.showMessageDialog(this, "OK, sigue activo");                    
                }
                System.out.println("no elimina vendedor (porque tiene ventas registradas)");
            }
            limpia_crud();
            reiniciar_id();
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpia_crud();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.getSelectedRow();
        
        //int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
        String id = jTable1.getValueAt(fila, 0).toString();
        String dni = jTable1.getValueAt(fila,1).toString();
        String nom = jTable1.getValueAt(fila, 2).toString();
        String tel = jTable1.getValueAt(fila, 3).toString();
        int estado = Integer.parseInt(jTable1.getValueAt(fila, 4).toString());
        String usu = jTable1.getValueAt(fila, 5).toString();
        
        idvend = id;
        txtNombres.setText(nom);
        txtTelefono.setText(tel);
        comboEstado.setSelectedIndex(estado);
        txtusuario.setText(usu);
        txtDni.setText(dni);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        char validar = evt.getKeyChar();
        
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            
            JOptionPane.showMessageDialog(rootPane, "Ingresa solo numeros");
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
