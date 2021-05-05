
package PS11601_PhongDTN_MOB1023_ASM;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class LoginForm extends javax.swing.JFrame {
    List<Account> list = new ArrayList<Account>();
    public LoginForm() {
        initComponents();
        this.setLocationRelativeTo(this);
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try{
            fis = new FileInputStream("DataAccounttt.dat");
            ois = new ObjectInputStream(fis);
            list = (List<Account>) ois.readObject();
            ois.close();
            fis.close();
        }catch(Exception e){
            
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).isRemember=true){
                txtUsername.setText(list.get(i).username);
                txtPassword.setText(list.get(i).password);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDangnNhap = new javax.swing.JLabel();
        lblTK = new javax.swing.JLabel();
        lblMK = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        chkRememberme = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDangnNhap.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lblDangnNhap.setForeground(new java.awt.Color(255, 51, 51));
        lblDangnNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDangnNhap.setText("ĐĂNG NHẬP ");

        lblTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTK.setForeground(new java.awt.Color(255, 255, 255));
        lblTK.setText("Tài Khoản");

        lblMK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMK.setForeground(new java.awt.Color(255, 255, 255));
        lblMK.setText("Mật Khẩu");

        btnLogin.setBackground(new java.awt.Color(255, 255, 255));
        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 51, 51));
        btnLogin.setText("ĐĂNG NHẬP");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnRegister.setBackground(new java.awt.Color(255, 255, 255));
        btnRegister.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 51, 51));
        btnRegister.setText("ĐĂNG KÍ");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        chkRememberme.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkRememberme.setForeground(new java.awt.Color(255, 51, 51));
        chkRememberme.setText("Remember me ?");
        chkRememberme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRemembermeActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/bacgroundLogin.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblMK))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(chkRememberme))
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblDangnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTK))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblMK)
                .addGap(23, 23, 23)
                .addComponent(chkRememberme)
                .addGap(45, 45, 45)
                .addComponent(btnLogin))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblDangnNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(btnRegister))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(lblTK))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("DataAccounttt.dat");
            ois = new ObjectInputStream(fis);
            list = (List<Account>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Error : "+e);
        }

        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        if(username.equalsIgnoreCase("Admin") && password.equals("admin")){
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công ! Xin chào "+username.toUpperCase()+" đến với chương trình Quản lý nhân viên !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
            FormASM formasm = new FormASM();
            formasm.setVisible(true);
        }else if(username.equalsIgnoreCase("phongdtn") && password.equals("ps11601")){
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công ! Xin chào "+username.toUpperCase()+" đến với chương trình Quản lý nhân viên !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
            FormASM formasm = new FormASM();
            formasm.setVisible(true);
        }else if(username.length()==0 || password.length()==0){
            JOptionPane.showMessageDialog(this, "Đăng nhập không thành công ! Tài khoản hoặc mật khẩu không đúng !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
            txtUsername.setBackground(Color.yellow);
            txtPassword.setBackground(Color.yellow);
        }else{
            for(int i=0;i<list.size();i++){
                if(username.equalsIgnoreCase(list.get(i).username) && password.equals(list.get(i).password)){
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công ! Xin chào "+username.toUpperCase()+" !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
                    FormASM formasm = new FormASM();
                    formasm.setVisible(true);
                    dispose();
                    if(chkRememberme.isSelected()){
                        list.get(i).isRemember=true;
                        try{
                            FileOutputStream fos= new FileOutputStream("DataAccountt.dat");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(list);
                            oos.close();
                        }catch(Exception e){
                            System.out.println("Error"+e);
                        }
                    }else{
                        list.get(i).isRemember=false;
                        try{
                            FileOutputStream fos= new FileOutputStream("DataAccountt.dat");
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(list);
                            oos.close();
                        }catch(Exception e){
                            System.out.println("Error : "+e);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void chkRemembermeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRemembermeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRemembermeActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        // TODO add your handling code here:
        Account account = new Account();
        account.username = txtUsername.getText().toString();
        account.password = txtPassword.getText().toString();
        list.add(account);
        if(account.username.length()==0 && account.password.length()==0){
            JOptionPane.showMessageDialog(this, "Đăng kí không thành công !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
            txtUsername.setBackground(Color.yellow);
            txtPassword.setBackground(Color.yellow);
        }else{
            try {
                FileOutputStream fos = new FileOutputStream("DataAccounttt.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                oos.close();
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.setBackground(Color.white);
                txtPassword.setBackground(Color.white);
                JOptionPane.showMessageDialog(this, "Bạn đã đăng kí thành công ! Xin mời nhập lại tài khoản và mật khẩu để sử dụng chương trình !!!","Thông báo đăng nhập",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.out.println("Error : "+e);
            }
        }
    }//GEN-LAST:event_btnRegisterActionPerformed


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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JCheckBox chkRememberme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDangnNhap;
    private javax.swing.JLabel lblMK;
    private javax.swing.JLabel lblTK;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
