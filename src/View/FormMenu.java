/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.VerificaLogin;
import java.awt.Toolkit;

/**
 *
 * @author Emerson
 */
public class FormMenu extends javax.swing.JFrame {

   
    /**
     * Creates new form Menu
     */
    public FormMenu(VerificaLogin log) {
        initComponents();
        txtVersao.setText("Version - 1.0.0");
        setExtendedState(MAXIMIZED_BOTH); //Maximizar a tela principal do sistema
        setIconImage(Toolkit.getDefaultToolkit().getImage("c:/SISOS/Imagem/sistema.png"));
        Inicializar(log);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jDesktopPaynelCentral = new javax.swing.JDesktopPane();
        txtVersao = new javax.swing.JLabel();
        labelNomeDeUsuarioLogado = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuCadastros = new javax.swing.JMenu();
        itemMenuUsuario = new javax.swing.JMenuItem();
        itemMenuCliente = new javax.swing.JMenuItem();
        itemMenuProdutos = new javax.swing.JMenuItem();
        itemMenuFilial = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        MenuMovimento = new javax.swing.JMenu();
        itemMenuServicos = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        MenuSair = new javax.swing.JMenu();
        itemMenuLogof = new javax.swing.JMenuItem();
        itemMenuSair = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Controle");

        txtVersao.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        jDesktopPaynelCentral.setLayer(txtVersao, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaynelCentralLayout = new javax.swing.GroupLayout(jDesktopPaynelCentral);
        jDesktopPaynelCentral.setLayout(jDesktopPaynelCentralLayout);
        jDesktopPaynelCentralLayout.setHorizontalGroup(
            jDesktopPaynelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaynelCentralLayout.createSequentialGroup()
                .addGap(0, 303, Short.MAX_VALUE)
                .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jDesktopPaynelCentralLayout.setVerticalGroup(
            jDesktopPaynelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaynelCentralLayout.createSequentialGroup()
                .addGap(0, 246, Short.MAX_VALUE)
                .addComponent(txtVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jDesktopPaynelCentral, java.awt.BorderLayout.CENTER);

        labelNomeDeUsuarioLogado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        labelNomeDeUsuarioLogado.setText("Usuario Logado");
        labelNomeDeUsuarioLogado.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(labelNomeDeUsuarioLogado, java.awt.BorderLayout.PAGE_START);

        MenuCadastros.setBorder(null);
        MenuCadastros.setText("CADASTRO");
        MenuCadastros.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemMenuUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/adicionar-usuario.png"))); // NOI18N
        itemMenuUsuario.setText("Usuarios");
        itemMenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuUsuarioActionPerformed(evt);
            }
        });
        MenuCadastros.add(itemMenuUsuario);

        itemMenuCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu_cliente.png"))); // NOI18N
        itemMenuCliente.setText("Cliente");
        itemMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuClienteActionPerformed(evt);
            }
        });
        MenuCadastros.add(itemMenuCliente);

        itemMenuProdutos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu_produtos.png"))); // NOI18N
        itemMenuProdutos.setText("Produto");
        itemMenuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuProdutosActionPerformed(evt);
            }
        });
        MenuCadastros.add(itemMenuProdutos);

        itemMenuFilial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuFilial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu_filial.png"))); // NOI18N
        itemMenuFilial.setText("Filial");
        itemMenuFilial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuFilialActionPerformed(evt);
            }
        });
        MenuCadastros.add(itemMenuFilial);

        jMenuBar1.add(MenuCadastros);

        jMenu6.setText(" | ");
        jMenuBar1.add(jMenu6);

        MenuMovimento.setText("MOVIMENTO");
        MenuMovimento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemMenuServicos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu-servicos.png"))); // NOI18N
        itemMenuServicos.setText("Serviços");
        itemMenuServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuServicosActionPerformed(evt);
            }
        });
        MenuMovimento.add(itemMenuServicos);

        jMenuBar1.add(MenuMovimento);

        jMenu7.setText(" | ");
        jMenuBar1.add(jMenu7);

        MenuSair.setText("SAIR");
        MenuSair.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        itemMenuLogof.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemMenuLogof.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu_logof.png"))); // NOI18N
        itemMenuLogof.setText("Logof");
        itemMenuLogof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuLogofActionPerformed(evt);
            }
        });
        MenuSair.add(itemMenuLogof);

        itemMenuSair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        itemMenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/menu_sair.png"))); // NOI18N
        itemMenuSair.setText("Sair");
        itemMenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuSairActionPerformed(evt);
            }
        });
        MenuSair.add(itemMenuSair);

        jMenuBar1.add(MenuSair);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Inicializar(VerificaLogin log){
        labelNomeDeUsuarioLogado.setText(log.getNome());
        
        if(log.getTipo().trim().equals("ADMINISTRADOR")){
            // TUDO LIBERADO PARA O ADMINISTRADOR
        }else if(log.getTipo().trim().equals("USUARIO")){
            itemMenuProdutos.setVisible(false);
            itemMenuCliente.setVisible(false);
            itemMenuFilial.setVisible(false);
        }
    }
    
    private void itemMenuServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuServicosActionPerformed
        new FormOrdemDeServico().setVisible(true);
    }//GEN-LAST:event_itemMenuServicosActionPerformed

    private void itemMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuClienteActionPerformed
        new FormClientes().setVisible(true);
    }//GEN-LAST:event_itemMenuClienteActionPerformed

    private void itemMenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuSairActionPerformed

        dispose();
       
    }//GEN-LAST:event_itemMenuSairActionPerformed

    private void itemMenuFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuFilialActionPerformed
        new FormFilial().setVisible(true);
    }//GEN-LAST:event_itemMenuFilialActionPerformed

    private void itemMenuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuProdutosActionPerformed
        new FormProdutos().setVisible(true);
    }//GEN-LAST:event_itemMenuProdutosActionPerformed

    private void itemMenuLogofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuLogofActionPerformed

        dispose();
        new FormLogin().setVisible(true);
        
    }//GEN-LAST:event_itemMenuLogofActionPerformed

    private void itemMenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuUsuarioActionPerformed
        new FormUsuarios().setVisible(true);
    }//GEN-LAST:event_itemMenuUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 VerificaLogin log = new VerificaLogin();
                new FormMenu(log).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuCadastros;
    private javax.swing.JMenu MenuMovimento;
    private javax.swing.JMenu MenuSair;
    private javax.swing.JMenuItem itemMenuCliente;
    private javax.swing.JMenuItem itemMenuFilial;
    private javax.swing.JMenuItem itemMenuLogof;
    private javax.swing.JMenuItem itemMenuProdutos;
    private javax.swing.JMenuItem itemMenuSair;
    private javax.swing.JMenuItem itemMenuServicos;
    private javax.swing.JMenuItem itemMenuUsuario;
    private javax.swing.JDesktopPane jDesktopPaynelCentral;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JLabel labelNomeDeUsuarioLogado;
    private javax.swing.JLabel txtVersao;
    // End of variables declaration//GEN-END:variables
}
