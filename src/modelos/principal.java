package modelos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class principal extends javax.swing.JFrame {
     
    //variables globales
    public static String nom_gfe;
    //definimos el nombre del superusario va a igualar
    public static String superuser = "mc";
    
    public principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        //definimos una variable para ver superusuario
        nom_gfe = login.a;
        
    }
    
    void  CentrarVentana(JInternalFrame frame){
        VentanaPrincipal.add(frame);
        Dimension dimension=VentanaPrincipal.getSize();
        Dimension Dframe=frame.getSize();
        frame.setLocation((dimension.width -Dframe.width)/2,(dimension.height-Dframe.height)/2);
        frame.show();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VentanaPrincipal = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        btn_ayuda = new javax.swing.JMenuItem();
        btn_salir = new javax.swing.JMenuItem();
        ventas = new javax.swing.JMenu();
        genera_ventas = new javax.swing.JMenuItem();
        genera_abono = new javax.swing.JMenuItem();
        add = new javax.swing.JMenu();
        add_cliente = new javax.swing.JMenuItem();
        add_producto = new javax.swing.JMenuItem();
        add_vendedor = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        corteVenta = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout VentanaPrincipalLayout = new javax.swing.GroupLayout(VentanaPrincipal);
        VentanaPrincipal.setLayout(VentanaPrincipalLayout);
        VentanaPrincipalLayout.setHorizontalGroup(
            VentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 573, Short.MAX_VALUE)
        );
        VentanaPrincipalLayout.setVerticalGroup(
            VentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 102, 255));
        jMenuBar1.setForeground(new java.awt.Color(51, 255, 255));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu2.png"))); // NOI18N
        menu.setText("Menu");

        btn_ayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ayuda2.png"))); // NOI18N
        btn_ayuda.setText("Ayuda");
        btn_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ayudaActionPerformed(evt);
            }
        });
        menu.add(btn_ayuda);

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/closed.png"))); // NOI18N
        btn_salir.setText("Cerrar sesion");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        menu.add(btn_salir);

        jMenuBar1.add(menu);

        ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/venta.png"))); // NOI18N
        ventas.setText("Ventas");

        genera_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ticket.png"))); // NOI18N
        genera_ventas.setText("GerenteVenta");
        genera_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genera_ventasActionPerformed(evt);
            }
        });
        ventas.add(genera_ventas);

        genera_abono.setText("GeneraAbono");
        genera_abono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genera_abonoActionPerformed(evt);
            }
        });
        ventas.add(genera_abono);

        jMenuBar1.add(ventas);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        add.setText("Añadir ");

        add_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/client.png"))); // NOI18N
        add_cliente.setText("Cliente");
        add_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_clienteActionPerformed(evt);
            }
        });
        add.add(add_cliente);

        add_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/prod.png"))); // NOI18N
        add_producto.setText("Producto");
        add_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_productoActionPerformed(evt);
            }
        });
        add.add(add_producto);

        add_vendedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vende.png"))); // NOI18N
        add_vendedor.setText("Vendedor");
        add_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_vendedorActionPerformed(evt);
            }
        });
        add.add(add_vendedor);

        jMenuBar1.add(add);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/graf2.png"))); // NOI18N
        jMenu4.setText("Reportes");

        jMenuItem7.setText("Reporte de Ventas");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem3.setText("Reporte ganancias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuBar1.add(jMenu4);

        corteVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/abono.png"))); // NOI18N
        corteVenta.setText("Corte Ventas");

        jMenuItem1.setText("inicial");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        corteVenta.add(jMenuItem1);

        jMenuItem2.setText("corte final");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        corteVenta.add(jMenuItem2);

        jMenuBar1.add(corteVenta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VentanaPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(VentanaPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ayudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ayudaActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        //salir
        //System.exit(0);
        login log=new login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void genera_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genera_ventasActionPerformed
        venta vv = new venta();
        CentrarVentana(vv);
    }//GEN-LAST:event_genera_ventasActionPerformed

    private void add_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_clienteActionPerformed
        Cliente cli = new Cliente();
        CentrarVentana(cli);
    }//GEN-LAST:event_add_clienteActionPerformed

    private void add_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_productoActionPerformed
        //validando super usuario
        if(nom_gfe.equals(superuser)){
            Producto pro = new Producto();
            CentrarVentana(pro);   
        }else{
            JOptionPane.showMessageDialog(null, "tu no tienes permisos");
        }
    }//GEN-LAST:event_add_productoActionPerformed

    private void add_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_vendedorActionPerformed
        if(nom_gfe.equals(superuser)){
            vendedor ven = new vendedor();
            CentrarVentana(ven);  
        }else{
            JOptionPane.showMessageDialog(null, "tu no tienes permisos");        }
    }//GEN-LAST:event_add_vendedorActionPerformed

    private void genera_abonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genera_abonoActionPerformed
        // TODO add your handling code here:
        abono ab = new abono();
        CentrarVentana(ab);
    }//GEN-LAST:event_genera_abonoActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        reporteVentas rv = new reporteVentas();
        CentrarVentana(rv);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        reporteGanancias rg = new reporteGanancias();
        CentrarVentana(rg);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        corteInicial ci = new corteInicial();
        CentrarVentana(ci);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        corteFinal cf  = new corteFinal();
        CentrarVentana(cf);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane VentanaPrincipal;
    private javax.swing.JMenu add;
    private javax.swing.JMenuItem add_cliente;
    private javax.swing.JMenuItem add_producto;
    private javax.swing.JMenuItem add_vendedor;
    private javax.swing.JMenuItem btn_ayuda;
    private javax.swing.JMenuItem btn_salir;
    private javax.swing.JMenu corteVenta;
    private javax.swing.JMenuItem genera_abono;
    private javax.swing.JMenuItem genera_ventas;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenu menu;
    private javax.swing.JMenu ventas;
    // End of variables declaration//GEN-END:variables

}
