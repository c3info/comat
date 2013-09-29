/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpr.comat.ui;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 */
public class ComatMDI extends javax.swing.JFrame {

    private ManterCliente mClienteForm;
    private ListarCliente lClienteForm;

    /**
     * Creates new form ComatMDI
     */
    public ComatMDI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuArquivo = new javax.swing.JMenu();
        mniClientes = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniSair = new javax.swing.JMenuItem();
        mnuAjuda = new javax.swing.JMenu();
        mniSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setName("Comat"); // NOI18N
        desktopPane.setOpaque(false);
        desktopPane.setPreferredSize(new java.awt.Dimension(800, 600));

        mnuArquivo.setMnemonic('C');
        mnuArquivo.setText("Arquivo");

        mniClientes.setMnemonic('l');
        mniClientes.setText("Clientes");
        mniClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniClientesActionPerformed(evt);
            }
        });
        mnuArquivo.add(mniClientes);
        mnuArquivo.add(jSeparator1);

        mniSair.setMnemonic('s');
        mniSair.setText("Sair");
        mniSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSairActionPerformed(evt);
            }
        });
        mnuArquivo.add(mniSair);

        menuBar.add(mnuArquivo);

        mnuAjuda.setMnemonic('a');
        mnuAjuda.setText("Ajuda");

        mniSobre.setMnemonic('s');
        mniSobre.setText("Sobre");
        mnuAjuda.add(mniSobre);

        menuBar.add(mnuAjuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniSairActionPerformed

    private void mniClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniClientesActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (lClienteForm == null) {
                    lClienteForm = new ListarCliente();
                    lClienteForm.setVisible(true);
                    desktopPane.add(lClienteForm);
                } else {
                    if (lClienteForm.isIcon()) {
                        try {
                            lClienteForm.setIcon(false);
                        } catch (PropertyVetoException ex) {
                            Logger.getLogger(ComatMDI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }//GEN-LAST:event_mniClientesActionPerformed

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
               UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ComatMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ComatMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ComatMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ComatMDI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ComatMDI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mniClientes;
    private javax.swing.JMenuItem mniSair;
    private javax.swing.JMenuItem mniSobre;
    private javax.swing.JMenu mnuAjuda;
    private javax.swing.JMenu mnuArquivo;
    // End of variables declaration//GEN-END:variables
}
