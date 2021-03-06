/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.enti_ararasdevfe.view;

import br.com.micheladrianomedeiros.enti_ararasdevfe.endpoint.GenericEndpoint;
import br.com.micheladrianomedeiros.enti_ararasdevfe.endpoint.Server;
import br.com.micheladrianomedeiros.enti_ararasdevfe.models.Operator;
import javax.swing.JOptionPane;
import tools.EncryptWihSha2;

/**
 *
 * @author Michel
 */
public class LoginView extends javax.swing.JFrame {

    /**
     * Creates new form LoginView
     */
    public LoginView() {
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

        JTFLogin = new javax.swing.JTextField();
        JPFPassword = new javax.swing.JPasswordField();
        JBLoggin = new javax.swing.JButton();
        JBLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ENTI-ARARASDEV");
        setResizable(false);

        JBLoggin.setText("Entrar");
        JBLoggin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLogginActionPerformed(evt);
            }
        });

        JBLogout.setText("Sair");
        JBLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JBLoggin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JBLogout))
                    .addComponent(JTFLogin)
                    .addComponent(JPFPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(JTFLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPFPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBLoggin)
                    .addComponent(JBLogout))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JBLogginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLogginActionPerformed
        String login = JTFLogin.getText();
        String pass = new String(JPFPassword.getPassword());

        EncryptWihSha2 e = new EncryptWihSha2();
        String password = e.encrypt(pass);

        GenericEndpoint<Operator> endpoint = new GenericEndpoint<>();
        String json = "{\"query\":\"{\\n  login(login:\\\"" + login + "\\\", \\n    password:\\\"" + password + "\\\"){\\n    id\\n    name\\n    pass\\n  }\\n}\"}";
        Operator operator = null;
        operator = endpoint.returnObject(json, Operator.class);

        if (operator != null) {
            Server.setID_OPERATOR(operator.getId());
            Server.setPASS(operator.getPass());

            System.out.println("ID_OPERATOR " + Server.getID_OPERATOR());
            System.out.println("PASS " + Server.getPASS());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Deu ruim", "Atenção!!!", 0);
        }

    }//GEN-LAST:event_JBLogginActionPerformed

    private void JBLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBLogoutActionPerformed

        GenericEndpoint<Operator> endpoint = new GenericEndpoint<>();
        String json = "{\"query\":\"{\\nlogout(id:\\\"" + Server.getID_OPERATOR() + "\\\", pass:\\\"" + Server.getPASS() + "\\\"){\\n  name\\n}\\n}\\n\"}";
        Operator operator = null;
        operator = endpoint.returnObject(json, Operator.class);
        JOptionPane.showMessageDialog(rootPane, "Até mais " + operator.getName() + ".", "Bye!!!", 1);
    }//GEN-LAST:event_JBLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBLoggin;
    private javax.swing.JButton JBLogout;
    private javax.swing.JPasswordField JPFPassword;
    private javax.swing.JTextField JTFLogin;
    // End of variables declaration//GEN-END:variables
}
