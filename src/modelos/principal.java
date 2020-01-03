package modelos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

public class principal extends javax.swing.JFrame {

    public principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
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
        add = new javax.swing.JMenu();
        add_cliente = new javax.swing.JMenuItem();
        add_producto = new javax.swing.JMenuItem();
        add_vendedor = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

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
        jMenuBar1.setForeground(new java.awt.Color(0, 51, 255));

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
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        menu.add(btn_salir);

        jMenuBar1.add(menu);

        ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sapo3.png"))); // NOI18N
        ventas.setText("Ventas");

        genera_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ticket.png"))); // NOI18N
        genera_ventas.setText("GerenteVenta");
        genera_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genera_ventasActionPerformed(evt);
            }
        });
        ventas.add(genera_ventas);

        jMenuBar1.add(ventas);

        add.setText("Añadir ");

        add_cliente.setText("Cliente");
        add_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_clienteActionPerformed(evt);
            }
        });
        add.add(add_cliente);

        add_producto.setText("Producto");
        add_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_productoActionPerformed(evt);
            }
        });
        add.add(add_producto);

        add_vendedor.setText("Vendedor");
        add_vendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_vendedorActionPerformed(evt);
            }
        });
        add.add(add_vendedor);

        jMenuBar1.add(add);

        jMenu4.setText("Reportes");

        jMenuItem7.setText("Reporte de Ventas");
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

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
        System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void genera_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genera_ventasActionPerformed
        
    }//GEN-LAST:event_genera_ventasActionPerformed

    private void add_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_clienteActionPerformed
        
    }//GEN-LAST:event_add_clienteActionPerformed

    private void add_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_productoActionPerformed
        
    }//GEN-LAST:event_add_productoActionPerformed

    private void add_vendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_vendedorActionPerformed
        vendedor ven = new vendedor();
        CentrarVentana(ven);
    }//GEN-LAST:event_add_vendedorActionPerformed

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
    private javax.swing.JDesktopPane VentanaPrincipal;
    private javax.swing.JMenu add;
    private javax.swing.JMenuItem add_cliente;
    private javax.swing.JMenuItem add_producto;
    private javax.swing.JMenuItem add_vendedor;
    private javax.swing.JMenuItem btn_ayuda;
    private javax.swing.JMenuItem btn_salir;
    private javax.swing.JMenuItem genera_ventas;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenu menu;
    private javax.swing.JMenu ventas;
    // End of variables declaration//GEN-END:variables
}
