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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


public class abono extends javax.swing.JInternalFrame {
    private PreparedStatement ps;
    private conexion con = new conexion();
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    
    //variables globales para buscar cliente
    public static String numeroCliente = "";
    public static String nombreCliente = "";
    
    public abono() {
        ps = null;
        initComponents();
        System.out.println(fechahoy());
        //limpiar tabla antes de usar siempre
        LimpiarTabla();
        //tabla todos los clientes
        listar2();
        //esta clase sirve para ver si se cerro desconectar de db
        addInternalFrameListener(new InternalFrameAdapter(){
            public void internalFrameClosing(InternalFrameEvent e) {
                System.out.println("se cerro Abono");
                con.desconectar();
                // do something  
            }
        });
    }
    
    //******************TABLA BUSCAR CLIENTE************************************
    private void listar(){
        tabla_clientes.setModel(getDatos());
    }
    //columnas tabla
    private DefaultTableModel setTitutlos(){
        DT.addColumn("IdCliente");
        DT.addColumn("Telefono");
        DT.addColumn("Nombres");
        DT.addColumn("Direccion");
        DT.addColumn("Estado");
        DT.addColumn("adeudo");
        return DT;
    }
    //muestra columnas
    private DefaultTableModel getDatos(){
        setTitutlos();
        if (!nombreCliente.equals("")) {
            nombreCliente = "%"+nombreCliente+"%";
        }
        if (!numeroCliente.equals("")) {
           numeroCliente = "%"+numeroCliente+"%" ;
        }
        String SQL_SELECT = "SELECT * FROM `cliente` WHERE Celular LIKE '"+numeroCliente+"' OR Nombres LIKE '"+nombreCliente+"'";
        //String SQL_SELECT = "SELECT * FROM cliente";
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
        } catch (SQLException e) {
            System.out.println("error mostrar ");
        }
      return DT;
    }
    //****************TABLA MOSTRAR TODO LOS CLIENTE****************************
    private void listar2(){
        tabla_clientes.setModel(getDatos2());
    }
    //columnas tabla
    private DefaultTableModel setTitutlos2(){
        DT.addColumn("IdCliente");
        DT.addColumn("Dni");
        DT.addColumn("Nombres");
        DT.addColumn("Direccion");
        DT.addColumn("Estado");
        DT.addColumn("adeudo");
        return DT;
    }
    //muestra columnas
    private DefaultTableModel getDatos2(){         
        setTitutlos2();
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
        } catch (SQLException e) {
            System.out.println("error mostrar ");
        }
      return DT;
    }
    
    //metodo limpiar tabla
    void LimpiarTabla() {
        for (int i = 0; i < DT.getRowCount(); i++) {
            DT.removeRow(i);
            i = i - 1;
        }
        DT.setColumnCount(0);
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
    
    //Trae el id del cliente buscando por celular o nombre
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
        txtFecha = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCelularCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        RealizarAbono = new javax.swing.JButton();
        VerAdeudo = new javax.swing.JButton();
        txtAdeudo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_clientes = new javax.swing.JTable();

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(txtFecha))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel15.setText("telefono");

        txtCelularCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCelularClienteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nombre");

        txtNombreCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreCliente.setForeground(new java.awt.Color(0, 51, 255));
        txtNombreCliente.setCaretColor(new java.awt.Color(0, 51, 255));
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(0, 51, 204));
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RealizarAbono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        RealizarAbono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/abono4.png"))); // NOI18N
        RealizarAbono.setText("Abonar");
        RealizarAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RealizarAbonoActionPerformed(evt);
            }
        });

        VerAdeudo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        VerAdeudo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
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
                .addComponent(RealizarAbono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VerAdeudo)
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
                    .addComponent(txtAdeudo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            JOptionPane.showMessageDialog(null, "No adeuda nadaa...");
        }
        else{
            insertar_abono();
            JOptionPane.showMessageDialog(null, "Se genero Abono");
            System.out.println("se cerro Abono");
            //desconectar al salir
            con.desconectar();
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

        if ((txtCelularCliente.getText().equals("")) && (txtNombreCliente.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "ingresa al menos un campo a buscar");
        }else{
            //manda el celular para saber el id
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
        }
        
    }//GEN-LAST:event_VerAdeudoActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //pasamos los textfield a var globales
        nombreCliente = txtNombreCliente.getText();
        numeroCliente = txtCelularCliente.getText();
        
        if ((nombreCliente.equals("")) && (numeroCliente.equals(""))) {
            JOptionPane.showMessageDialog(null, "ingresa al menos un campo a buscar");
        }else{
            LimpiarTabla();
            listar();
            //seleccionar id de la tabla
            //int fila = buscaCliente.getSelectedRow();
            //int id = Integer.parseInt(buscaCliente.getValueAt(fila, 0).toString());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tabla_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_clientesMouseClicked
        int fila = tabla_clientes.getSelectedRow();
        
        //int id = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());
        String id = tabla_clientes.getValueAt(fila, 0).toString();
        String dni = tabla_clientes.getValueAt(fila,1).toString();
        String nom = tabla_clientes.getValueAt(fila, 2).toString();
        String dir = tabla_clientes.getValueAt(fila, 3).toString();
        int estado = Integer.parseInt(tabla_clientes.getValueAt(fila, 4).toString());
        String adeudo = tabla_clientes.getValueAt(fila, 5).toString();
        
        
        txtCelularCliente.setText(dni);
        txtNombreCliente.setText(nom);
        txtAdeudo.setText(adeudo);
    }//GEN-LAST:event_tabla_clientesMouseClicked
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RealizarAbono;
    private javax.swing.JButton VerAdeudo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_clientes;
    private javax.swing.JTextField txtAdeudo;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCelularCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
