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

public class venta extends javax.swing.JInternalFrame {
    //variables para conexion db
    private PreparedStatement ps;
    private conexion con;
    private DefaultTableModel DT = new DefaultTableModel();
    private ResultSet RS;
    //variables globales
    private static double cant = 0;
    public static int ns = 0;
    private static int idventas = 0;
    private static int band = 0;
    public static String nom_cliente ="";
    //constructor de venta
    public venta() {
        ps = null;
        initComponents();
        //inicializa tabla ventas
        jTable1.setModel(getDatos3());
        LimpiarTabla();
        //inicializa metodo obtener numero serie
        numserie();
        System.out.println(fechahoy());
        //para obtener vendedor usuario
        txtVendedor.setText(login.a);
        //el total sea igual a cero
        txtTotalPagar.setText("0");
    }
    
    //metodo que reinicia el id de venta
    public void reiniciar_id(){
        String reiniciarid = "ALTER TABLE ventas AUTO_INCREMENT = 1";
        try {
            ps = con.getConnection().prepareStatement(reiniciarid);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("no jalo el REINICIAR ID");
        }
    }
    //reinicia el id  de detalle de ventas
    public void reiniciar_id2(){
        String reiniciarid = "ALTER TABLE detalle_ventas AUTO_INCREMENT = 1";
        try {
            ps = con.getConnection().prepareStatement(reiniciarid);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("no jalo el REINICIAR ID");
        }
    }
    //metodo para obtener numero de serie
    public void numserie(){
        int serie = 0;
        String fecha = "";
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
        ns = res;
        String se = String.valueOf(res);
        txtSerie.setText(se);
        
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
    
    //********************AQUI SON SOLO TABLAS A MOSTRAR************************************************
    //titulos tabla cliente y tabla clietes
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
        band = 0;
        
        if (!usu.equals("")) {
            p = "%";
        }
        if (!dni.equals("")) {
            o = "%";
        }
        
        String SQL_SELECT = "SELECT * FROM cliente WHERE Nombres LIKE '"+usu+p+"' OR Celular LIKE '"+dni+o+"'";
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
                band = band+1;
            }
            //System.out.println("si hizo el desmadre");
        } catch (SQLException e) {
            System.out.println("error de select");
        }
        
      return DT;
    }
    
    //titulos tabla venta y tabla devuelve por folio o num serie
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
        
        String SQL_SELECT = "SELECT * FROM ventas WHERE NumeroSerie = "+ns+"";
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
    //tabla de detalle_ventas
    private DefaultTableModel setTitutlos3(){
        con = new conexion();
        DT.addColumn("IdDetalleVentas");
        DT.addColumn("producto");
        DT.addColumn("Cantidad");
        DT.addColumn("PrecioVenta");
        return DT;
    }
    private DefaultTableModel getDatos3(){
        
        String SQL_SELECT = "SELECT dv.IdDetalleVentas, p.Nombres, dv.Cantidad, dv.PrecioVenta FROM detalle_ventas dv join producto p ON dv.IdProducto=p.IdProducto JOIN ventas v ON dv.IdVentas=v.IdVentas WHERE v.NumeroSerie="+ns+"";
        try {
            setTitutlos3();
            
            ps = con.getConnection().prepareStatement(SQL_SELECT);
            RS = ps.executeQuery();
            Object[] fila = new Object[4];
            while (RS.next()){
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getInt(3);
                fila[3] = RS.getDouble(4);
                DT.addRow(fila);
            }
            //System.out.println("si hizo el desmadre");
        } catch (SQLException e) {
            System.out.println("error de select");
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
    //******************************************************************************************
    
    //metodo para calcular precio
    void calcular(){
        if (cant != 0) {
            int veces = (int) txtCantidad.getValue();
            double r = veces*cant;
            String res = String.valueOf(r);
            txtPrecio.setText(res);
        }
    }
    
    //metodo retorna id cliente
    public int id_cli(String dni){
        int baba = 0;
        String SQL_select = "SELECT IdCliente FROM cliente WHERE Celular='"+dni+"'";
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                baba = RS.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ID cliente");
        }
        return baba;
    }
    
    //metodo para obtener id vendedor
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

    //obtener id productooo
    public int id_prod(){
        String maroji = txtCodProd.getText().toString();
        
        String SQL_select = "SELECT * FROM `producto` WHERE Codigo_product = "+maroji+"";
        int lolo = 0;
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            while (RS.next()) {                
                lolo = RS.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no dio el id de producto");
        }
        
        return lolo;
    }
    
    //insertar a detalle de venta
    public void in_deta_venta(){
        //inicializa detalle de venta id
        reiniciar_id2();
        //insertar idventas
        exis_fol();
        //cantidad del producto
        int cacantidad = (int) txtCantidad.getValue();
        //precio por todo
        String prec = txtPrecio.getText().toString();
        
        
        String SQL_insert = "INSERT INTO `detalle_ventas` (IdVentas, IdProducto, Cantidad, PrecioVenta) VALUES ('"+idventas+"', '"+id_prod()+"', '"+cacantidad+"', '"+prec+"');";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_insert);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no guardo en detalle_venta");
        }
    }
    
    //insertar a venta
    public void in_venta(){
        //reiniciar id
        reiniciar_id();
        //obtener idcliente
        String cai = txtCodCliente.getText().toString();
        int idc = id_cli(cai);
        String ic = String.valueOf(idc);
        if (cai.equals("") || idc==0) {
            ic = null;
        }
        //obtener idvendedor
        int idv = id_ven();
        //insertar numero de serie
        String nums = txtSerie.getText();
        //insertar la fecha de hoy
        String ffec = fechahoy();
        //insertar la cantidad
        double costo = Double.parseDouble(txtPrecio.getText().toString());
        //insertar a ventas
        String SQL_insert = "INSERT INTO ventas(IdCliente, IdVendedor, NumeroSerie, FechaVentas, Monto, tipo_pago) VALUES ("+ic+", "+idv+", "+nums+", '"+ffec+"', "+costo+", 'e')";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_insert);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no guardo en venta");
        }
    }
    
    //validar si existe ese folio
    public int exis_fol(){
        int sino = 0;
        
        String SQL_sell = "SELECT NumeroSerie,IdVentas FROM `ventas` WHERE NumeroSerie="+ns+"; ";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_sell);
            RS = ps.executeQuery();
            
            while (RS.next()) {                
                sino = RS.getInt(1);
                idventas = RS.getInt(2);
            }
        } catch (SQLException ex) {
            System.out.println("no se puso numSerie");
        }
        return sino;
    }
    
    
    //total a pagar 
    public double totalpagar(){
        double totalfinal = 0;
        
        String SQL_SUM = "SELECT SUM(PrecioVenta) FROM `detalle_ventas` WHERE IdVentas = "+idventas+"";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_SUM);
            RS = ps.executeQuery();
            while (RS.next()) {                
                totalfinal = RS.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Producto No registrado");
        }
        return totalfinal;
    }
    
    //actualizar venta en precio
    public void actu_venta(String tpago){
        String monto = txtTotalPagar.getText().toString();
        String SQL_UPDATE = "UPDATE `ventas` SET Monto="+monto+",  WHERE IdVentas = "+idventas+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("no sirve la actualizar");
        }
    }

    //actualizar el stock
    public void act_stock(){
        //catidad que queda
        int veces = (int) txtCantidad.getValue();
        int sto = Integer.parseInt(txtStock.getText().toString());
        
        int actu_stok = sto - veces;
        
        int uli = id_prod();
        
        String SQL_UPDATE = "UPDATE `producto` SET Stock="+actu_stok+" WHERE IdProducto = "+uli+"";
        try {
            ps = con.getConnection().prepareStatement(SQL_UPDATE);
            ps.execute();
            
            String act= String.valueOf(actu_stok);
            txtStock.setText(act);
            
        } catch (SQLException e) {
            System.out.println("no sirve la actualizar");
        }
    }
    
    
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

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("venta");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTO DE VENTA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("venta");

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
                .addContainerGap(147, Short.MAX_VALUE)
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

        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });

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
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

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
        txtVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVendedorActionPerformed(evt);
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

        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/money.png"))); // NOI18N
        btnGenerar.setText("GENERAR VENTA");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

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
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //********************************BOTONES DE INTERFAZ*****************************************************************
    
    //boton buscar cliente
    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        int r;
        
        LimpiarTabla();
        String cliente = txtCliente.getText().toString();
        String dni = txtCodCliente.getText().toString();
        jTable1.setModel(getDatos(cliente, dni));
        
        if (band == 0) {
            r = JOptionPane.showConfirmDialog(this, "Cleinte No Registrado, Desea Reagistrar?");
                if (r == 0) {
                    Cliente cli = new Cliente();
                    principal.VentanaPrincipal.add(cli);
                    cli.setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "hacer ticket sin cliente registrado");
                    txtCodCliente.setText("");
                    txtCliente.setText("");
                }
        }
    }//GEN-LAST:event_btnBuscarClienteActionPerformed
    
    //boton buscar producto por codigo de barras
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LimpiarTabla();
        int hay = 0;
        int g;
        String cod_pro = txtCodProd.getText().toString();
        
        String SQL_select = "SELECT p.IdProducto, p.Nombres,p.Precio,p.Stock  FROM producto as p WHERE Codigo_product = '"+cod_pro+"'";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_select);
            RS = ps.executeQuery();
            
            while (RS.next()) {                
                txtProducto.setText(RS.getString(2));
                txtPrecio.setText(RS.getString(3));
                cant = Double.parseDouble(RS.getString(3));
                txtStock.setText(RS.getString(4));
                hay = hay+1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Producto No registrado");
        }
        if (hay == 0) {
            g = JOptionPane.showConfirmDialog(this, "Producto No Registrado, Desea Reagistrar?");
            if (g == 0) {
                Producto pr = new Producto();
                principal.VentanaPrincipal.add(pr);
                pr.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(this, "intenta otro");
                txtCodProd.setText("");
            }
        }
        txtCantidad.setValue(1);
    }//GEN-LAST:event_jButton2ActionPerformed
    //boton agrega producto a ventas
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /*
        1.-limpiamos tablas
        2.-calculamos el precio base la canntidad de productos
        3.-verificamos que exista esa venta por el folio y si no la creamos
        4.-insertamos el detalle de venta
        5.-llamamos al metodo de lo total a pagar
        6.-hablamos a la funcion de actualizar el stock
        */
        LimpiarTabla();
        //calcula el precio
        calcular();
        //calamos si existe el folio de venta y si no para crearlo
        System.out.println(exis_fol());
        if (exis_fol() == 0) {
            in_venta();
            System.out.println("no existeee");
        }
        //llamar a insertar detalle venta
        in_deta_venta();
        //total final a pagar
        String totfin = String.valueOf(totalpagar());
        txtTotalPagar.setText(totfin);
        //actualizar stock
        act_stock();
        
        jTable1.setModel(getDatos3());
    }//GEN-LAST:event_jButton3ActionPerformed
    //boton final de generar venta
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        nom_cliente = txtCliente.getText().toString();
        //definimos la variable que sera tipo de pago
        String[] options = {"Tarjeta", "Efectivo", "Abono"};
        int seleccion = JOptionPane.showOptionDialog(null, "Es necesario que seleccione una opcion", "Titulo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        String tpago;
        //
        if (seleccion == 0) {
            JOptionPane.showMessageDialog(this, "pago con Tarjeta");
            tpago = "t";
            actu_venta(tpago);
            imprimir2 imp = new imprimir2();
            imp.setVisible(true);
            dispose();
        }else if (seleccion == 1){
            JOptionPane.showMessageDialog(this, "pago con Efectivo");
            tpago = "e";
            actu_venta(tpago);
            imprimir2 imp = new imprimir2();
            imp.setVisible(true);
            dispose();
        }else if (seleccion == 2){
            JOptionPane.showMessageDialog(this, "pago en Abono");
            tpago = "a";
            actu_venta(tpago);
            imprimir2 imp = new imprimir2();
            imp.setVisible(true);
            dispose();
        }else {
            JOptionPane.showMessageDialog(this, "cancelo");
        }
        

        
        
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
        String SQL_del = "DELETE FROM `detalle_ventas` WHERE IdVentas = (SELECT v.IdVentas FROM ventas v where v.NumeroSerie = "+ns+")";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_del);
            ps.execute();
            
        } catch (SQLException ex) {
            System.out.println("no elimina detalle de venta");
        }
        del_ventas();
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVendedorActionPerformed
    
    public void del_ventas(){
        String SQL_elim = "DELETE FROM `ventas` WHERE NumeroSerie = "+ns+"";
        
        try {
            ps = con.getConnection().prepareStatement(SQL_elim);
            ps.execute();
            
        } catch (SQLException ex) {
            System.out.println("no elimina ventas");
        }
    }
    

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
