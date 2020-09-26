/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.CadastroDeClientes;
import Classes.SoNumeros;
import Classes.SoLetrasMaiusculas;
import Controller.ClienteTabelaModel;
import Controller.GeradorDePdf;
import Classes.BuscaCEP;
import Model.ClientesDAO;
import Model.Database;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Emerson
 */
public class FormClientes extends javax.swing.JFrame {

    private Connection con;

    ClienteTabelaModel tabelaClientes = new ClienteTabelaModel();

    DefaultListModel MODELO;

    int Enter = 0;

    public FormClientes() {
        initComponents();
        Iniciar();
        BuscarEstado();          // Buscando os Estados no BD
        BuscarCodigoDeCliente(); // Buscando o proximo código a ser cadastrado no banco
        Lista.setVisible(false);
        MODELO = new DefaultListModel();
        Lista.setModel(MODELO);
        Mascaras();
        jtableClientes.setModel(tabelaClientes);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Lista = new javax.swing.JList<>();
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jTelefone = new javax.swing.JFormattedTextField();
        jCnpj = new javax.swing.JFormattedTextField();
        jCpf = new javax.swing.JFormattedTextField();
        jCep = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        comboboxEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableClientes = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        btnSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(255, 255, 255));
        btnSair.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CADASTRO DE CLIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAlterar.setBackground(new java.awt.Color(255, 255, 255));
        btnAlterar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editar.png"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListaMousePressed(evt);
            }
        });
        jLayeredPane1.add(Lista, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 570, 110));

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });
        jLayeredPane1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 11, 580, -1));

        jLabel2.setText("Código:");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 14, -1, -1));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });
        jLayeredPane1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 11, 50, -1));

        jLabel3.setText("Nome:");
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 14, -1, -1));

        jLabel6.setText("Numero:");
        jLayeredPane1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, -1, -1));

        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });
        jLayeredPane1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 80, 40, -1));

        jLabel7.setText("Cep:");
        jLayeredPane1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jLabel8.setText("E-Mail:");
        jLayeredPane1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));
        jLayeredPane1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 297, 260, -1));

        jLabel9.setText("E-Mail 2:");
        jLayeredPane1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, -1, -1));
        jLayeredPane1.add(txtEmail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(418, 297, 340, -1));

        jLabel14.setText("Telefone:");
        jLayeredPane1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        jLabel15.setText("CNPJ:");
        jLayeredPane1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel16.setText("CPF:");
        jLayeredPane1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, -1, -1));

        jLabel13.setText("Bairro:");
        jLayeredPane1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        txtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBairroActionPerformed(evt);
            }
        });
        jLayeredPane1.add(txtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 152, -1));

        jTelefone.setToolTipText("");
        jLayeredPane1.add(jTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 207, -1));
        jLayeredPane1.add(jCnpj, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, 207, -1));
        jLayeredPane1.add(jCpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, 152, -1));

        jCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCepKeyPressed(evt);
            }
        });
        jLayeredPane1.add(jCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 80, 90, -1));

        jLabel4.setText("Endereço:");
        jLayeredPane1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, -1));

        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });
        jLayeredPane1.add(txtEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 190, -1));

        jLabel10.setText("Cidade:");
        jLayeredPane1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));
        jLayeredPane1.add(txtCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 350, -1));

        jLabel11.setText("Estado:");
        jLayeredPane1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

        jLayeredPane1.add(comboboxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 58, -1));

        jLabel12.setText("Pais:");
        jLayeredPane1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        txtPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisActionPerformed(evt);
            }
        });
        jLayeredPane1.add(txtPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 149, -1));

        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 768, -1));

        jTabbedPane1.addTab("Cadastro", jPanel1);

        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jButton1.setText("Buscar Clientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jtableClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtableClientes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pdf.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Relatórios", jPanel3);

        btnBuscar1.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancelar.png"))); // NOI18N
        btnBuscar1.setText("Cancelar");
        btnBuscar1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        atualizar();

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        Buscar();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        Salvar();

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        Lista.setVisible(false);
        Enter = 1;
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased
        if (Enter == 0) {
            ListaDePesquisa();
        } else {
            Enter = 0;
        }
 
    }//GEN-LAST:event_txtNomeKeyReleased

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed

    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed

    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroActionPerformed

    private void ListaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaMousePressed
        txtNome.setText(Lista.getSelectedValue());
        Lista.setVisible(false);
    }//GEN-LAST:event_ListaMousePressed

    private void InsereNaTabela() {

        ClientesDAO cliDao = new ClientesDAO();

        List<CadastroDeClientes> lista = cliDao.InsereNaTabela();

        for (int x = 0; x < lista.size(); x++) {

            CadastroDeClientes cli = new CadastroDeClientes();

            cli.setNome(lista.get(x).getNome());
            cli.setEndereco(lista.get(x).getEndereco());
            cli.setTelefone(lista.get(x).getTelefone());
            cli.setData(lista.get(x).getData());

            tabelaClientes.addRow(cli);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InsereNaTabela();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        GeradorDePdf geraPdf = new GeradorDePdf();
        geraPdf.GeraPDFClientes();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed

    }//GEN-LAST:event_jTabbedPane1MousePressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed

    }//GEN-LAST:event_jPanel3MousePressed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        Limpar();
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void jCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja Buscar o CEP", "escolha dois", JOptionPane.YES_NO_OPTION);
            //verfica se a resposta é verdadeira
            if (resposta == JOptionPane.YES_OPTION) {
                BuscaCEP();
            }

        }
    }//GEN-LAST:event_jCepKeyPressed

    private void BuscaCEP(){
        String CEP = jCep.getText();

        BuscaCEP buscaCep = new BuscaCEP();

        try {
            txtEndereco.setText(buscaCep.getEndereco(CEP)); // Insere os dados de endereço da classe de buscacep
            txtBairro.setText(buscaCep.getBairro(CEP));
            txtCidade.setText(buscaCep.getCidade(CEP));
            comboboxEstado.setSelectedItem(buscaCep.getUF(CEP));

        } catch (IOException ex) {
            Logger.getLogger(FormFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Mascaras() {

        MaskFormatter maskTelefone, maskCnpj, maskCpf, maskCep;

        try {
            maskTelefone = new MaskFormatter("(##) ####-####");
            maskTelefone.install(jTelefone);

            maskCnpj = new MaskFormatter("##.###.###/####-##");
            maskCnpj.install(jCnpj);

            maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(jCpf);

            maskCep = new MaskFormatter("#####-###");
            maskCep.install(jCep);

        } catch (ParseException ex) {
            Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Iniciar() {
        txtCodigo.setEnabled(false);
        txtNome.setDocument(new SoLetrasMaiusculas());
        txtCodigo.setDocument(new SoNumeros());
        txtNumero.setDocument(new SoNumeros());
    }

    private void BuscarCodigoDeCliente() {
        ClientesDAO cliDao = new ClientesDAO();
        CadastroDeClientes cli = new CadastroDeClientes();
        
        cliDao.BuscarCodigoDeCliente(cli);
        
        txtCodigo.setText(cli.getCodigo());
    }

    private void ListaDePesquisa() {
       MODELO.removeAllElements(); // Remove os elementos
       CadastroDeClientes cli = new CadastroDeClientes(); // chama a classe de cadastro
      
       cli.setNome(txtNome.getText()); // Busca os valores digitados no campo de nome
       
       ClientesDAO cliDao = new ClientesDAO(); // chama a classe de CkietesDAO

       List<CadastroDeClientes> lista = cliDao.ListaDePesquisa(cli); //Chama a lista da Chasse DAO pegando os parametros da Classe de Cadastro e Enviando esse parametros para ela
       
        
            boolean validador = false; // Validador da LISTA ficar oculta ou não
            for(int x=0; x < lista.size(); x++) {
                MODELO.addElement(lista.get(x).getNome()); // Adiciona na lista o Array que foi adiocionado na classe DAO no metodo da Lista de Pesquisa
                validador = true;
            }
            if (validador) {
                Lista.setVisible(true);
            } else {
                Lista.setVisible(false);
            } 
            
    }

    // CRUD
    private void Conexao() { // Classe de Conexão com o Banco de Dados
        this.con = Database.getConnection();
    }

    private void Salvar() {

        CadastroDeClientes cli = new CadastroDeClientes();

        ClientesDAO cliDao = new ClientesDAO();

        cli.setNome(txtNome.getText());
        cli.setEndereco(txtEndereco.getText());
        cli.setNumero(txtNumero.getText());
        cli.setTelefone(jTelefone.getText());
        cli.setCep(jCep.getText());
        cli.setCidade(txtCidade.getText());
        cli.setEstado((String) comboboxEstado.getSelectedItem());
        cli.setPais(txtPais.getText());
        cli.setEmail(txtEmail.getText());
        cli.setEmail2(txtEmail2.getText());
        cli.setCnpj(jCnpj.getText());
        cli.setCpf(jCpf.getText());
        cli.setBairro(txtBairro.getText());

        cliDao.Salvar(cli);
        Limpar();
        BuscarCodigoDeCliente();
    }

    private void Buscar() {

        CadastroDeClientes cli = new CadastroDeClientes();
        cli.setNome(txtNome.getText());

        ClientesDAO cliDao = new ClientesDAO();

        cliDao.Buscar(cli);

        txtCodigo.setText(cli.getCodigo());
        txtNome.setText(cli.getNome());
        txtEndereco.setText(cli.getEndereco());
        txtNumero.setText(cli.getNumero());
        jTelefone.setText(cli.getTelefone());
        jCnpj.setText(cli.getCnpj());
        jCpf.setText(cli.getCpf());
        jCep.setText(cli.getCep());
        txtCidade.setText(cli.getCidade());
        comboboxEstado.setSelectedItem(cli.getEstado());
        txtPais.setText(cli.getPais());
        txtEmail.setText(cli.getEmail());
        txtEmail2.setText(cli.getEmail2());
        txtBairro.setText(cli.getBairro());

    }

    private void BuscarEstado() {
        
        ClientesDAO cliDao = new ClientesDAO();
        List<String> lista = cliDao.BuscarEstado();
        CadastroDeClientes cli = new CadastroDeClientes();

            for(int x=0; x<lista.size();x++){
                comboboxEstado.addItem(lista.get(x));
            }

        

    }

    private void atualizar() {

        CadastroDeClientes cli = new CadastroDeClientes();
        ClientesDAO cliDao = new ClientesDAO();

        cli.setCodigo(txtCodigo.getText());
        cli.setNome(txtNome.getText());
        cli.setEndereco(txtEndereco.getText());
        cli.setNumero(txtNumero.getText());
        cli.setTelefone(jTelefone.getText());
        cli.setCep(jCep.getText());
        cli.setCidade(txtCidade.getText());
        cli.setEstado((String) comboboxEstado.getSelectedItem());
        cli.setPais(txtPais.getText());
        cli.setEmail(txtEmail.getText());
        cli.setEmail2(txtEmail2.getText());
        cli.setCnpj(jCnpj.getText());
        cli.setCpf(jCpf.getText());
        cli.setBairro(txtBairro.getText());

        cliDao.atualizar(cli);
        Limpar();
        
    }

    private void Limpar() {
        jCep.setText("");
        txtCidade.setText("");
        txtCodigo.setText("");
        jCnpj.setText("");
        txtEmail.setText("");
        txtEmail2.setText("");
        txtEndereco.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtPais.setText("");
        jCpf.setText("");
        jTelefone.setText("");
        txtBairro.setText("");
        BuscarCodigoDeCliente();
    }

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
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Lista;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboboxEstado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFormattedTextField jCep;
    private javax.swing.JFormattedTextField jCnpj;
    private javax.swing.JFormattedTextField jCpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField jTelefone;
    private javax.swing.JTable jtableClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPais;
    // End of variables declaration//GEN-END:variables

}
