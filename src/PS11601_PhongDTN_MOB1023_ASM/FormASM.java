
package PS11601_PhongDTN_MOB1023_ASM;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;
import PS11601_PhongDTN_MOB1023_ASM.LoginForm;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormASM extends javax.swing.JFrame {
    List<Employee> list = new ArrayList<>();
    int timeRun = 0;
    int index = 0;
    public FormASM() {
        initComponents();
        this.setSize(1050, 700);
        this.setLocationRelativeTo(this);
    }
    public void  Clock(){
        new Thread(){
        public void run(){
        while(timeRun == 0){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
            sdf.applyPattern("hh:mm:ss aa");
            String txt = sdf.format(date);
            lblDongHo.setText(txt);
                }
            }
        }.start();
    }
    private void addEmployee(){
            Employee employee = new Employee();
            employee.maNV = txtMaNhanVien.getText();
            employee.hoTen = txtHoVaTen.getText();
            employee.tuoi = Integer.parseInt(txtTuoi.getText().toString());
            employee.eMail = txtEmail.getText();
            employee.luong = Double.parseDouble(txtLuong.getText().toString());
            employee.IMG = lblImage.getIcon().toString();
            list.add(employee);
    }
    private void fillToTable(){
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        model.setRowCount(0);
        for(Employee emp : list){
            Object[] row = new Object[]{emp.maNV,emp.hoTen,emp.tuoi,emp.eMail,emp.luong};
            model.addRow(row);
        }
    }
    
    private void updateEmployee(){
        Employee emp = new Employee();
        emp.maNV = txtMaNhanVien.getText();
        emp.hoTen = txtHoVaTen.getText();
        emp.tuoi = Integer.parseInt(txtTuoi.getText().toString());
        emp.eMail = txtEmail.getText();
        emp.luong = Double.parseDouble(txtLuong.getText());
        emp.IMG = lblImage.getIcon().toString();
        list.set(index, emp);
    }
    
    private void showDetail(){
        Employee emp = list.get(index);
        txtMaNhanVien.setText(emp.maNV);
        txtHoVaTen.setText(emp.hoTen);
        txtTuoi.setText(emp.tuoi+"");
        txtEmail.setText(emp.eMail);
        txtLuong.setText(emp.luong+"");
        lblImage.setIcon(new ImageIcon(emp.IMG));
        txtMaNhanVien.enable(false);
    }
    
    private void removeEmployee(){
        index = tblEmployee.getSelectedRow();
        list.remove(index);
        updateStatusRecord();
    }
    
    private void updateStatusRecord(){
        lblStatus.setText("Record : "+(index+1)+" of "+list.size());
    }
    
    private void saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream("Data.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos); 
            oos.writeObject(list);
            oos.close();
        }catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    private void OpenFile(){
        FileInputStream fis = null;
        ObjectInputStream ois =  null;
        try {
            fis = new FileInputStream("Data.dat");
            ois = new ObjectInputStream(fis);
            list = (List<Employee>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Có lỗi : "+e);
        }
    }
    public void sortName(){
        Comparator<Employee> comp = new Comparator<Employee>() {
            @Override
            public int compare(Employee t, Employee t1) {
                return t.hoTen.compareTo(t1.hoTen);
            }
        };
        Collections.sort(list, comp);
    }
    
    public void sortPrice(){
        Comparator<Employee> comp = new Comparator<Employee>() {
            @Override
            public int compare(Employee t, Employee t1) {
                Double d1 = t.luong;
                Double d2 = t1.luong;
                return d1.compareTo(d2);
            }
        };
        Collections.sort(list, comp);
    }
    
    public void sortAge(){
        Comparator<Employee> comp = new Comparator<Employee>() {
            @Override
            public int compare(Employee t, Employee t1) {
                int d1 = t.tuoi;
                int d2 = t1.tuoi;
                return d1 < d2 ? 1:-1;
            }
        };
        Collections.sort(list, comp);
    }
    private void Find(){
        String maNhanVien;
        int i;
        maNhanVien = JOptionPane.showInputDialog(null, "Nhập vào mã nhân viên cần tìm : ");
        for(i=0;i<list.size();i++){
            Employee employee = list.get(i);
            if(employee.maNV.equalsIgnoreCase(maNhanVien)){
                JOptionPane.showMessageDialog(this, "Đã tìm thấy nhân viên, Mã nhân viên : "+employee.maNV+" - "+"Họ và tên : "+employee.hoTen);
                index = i;
                showDetail();
                updateStatusRecord();
                break;
            }
        }
        if(i==list.size()){
            try {
                this.clearForm();
                JOptionPane.showMessageDialog(this, "Không tìm thấy Nhân viên");
                txtMaNhanVien.enable(true);
                tblEmployee.setRowSelectionAllowed(false);
                lblStatus.setText("Record : " + " of "+list.size());
            } catch (Exception e) {
                
            }
        }
    }
    public void clearForm(){
        txtMaNhanVien.setText("");
        txtHoVaTen.setText("");
        txtTuoi.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
        btnSave.setText("Save");
        lblImage.setIcon(new ImageIcon("/PS11601_PhongDTN_MOB1023_ASM/Images/user_add.jpg"));
        txtMaNhanVien.requestFocus();
    }
    public void ChooserImage(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("3 Extensions Supported","jpg","png","jpeg");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);
        if(selected == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            String getSelectedImage = file.getAbsolutePath();
            JOptionPane.showMessageDialog(this,"Hình ảnh có đường dẫn : "+getSelectedImage+" đã được chọn","Thông báo chọn hình ảnh",JOptionPane.INFORMATION_MESSAGE);
            ImageIcon imageIcon = new ImageIcon(getSelectedImage);
            lblImage.setIcon(imageIcon);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        lblHoVaTen = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        lblTuoi = new javax.swing.JLabel();
        txtTuoi = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblLuong = new javax.swing.JLabel();
        txtLuong = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        btnSortName = new javax.swing.JButton();
        btnSortPrice = new javax.swing.JButton();
        btnSortAge = new javax.swing.JButton();
        lblDongHo = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        btnChoseeImage = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(310, 50, 398, 44);

        lblMaNhanVien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMaNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblMaNhanVien.setText("MÃ NHÂN VIÊN");
        getContentPane().add(lblMaNhanVien);
        lblMaNhanVien.setBounds(50, 130, 110, 17);

        txtMaNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMaNhanVienMouseClicked(evt);
            }
        });
        txtMaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhanVienActionPerformed(evt);
            }
        });
        getContentPane().add(txtMaNhanVien);
        txtMaNhanVien.setBounds(200, 120, 181, 31);

        lblHoVaTen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHoVaTen.setForeground(new java.awt.Color(255, 255, 255));
        lblHoVaTen.setText("HỌ VÀ TÊN");
        getContentPane().add(lblHoVaTen);
        lblHoVaTen.setBounds(50, 190, 79, 17);
        getContentPane().add(txtHoVaTen);
        txtHoVaTen.setBounds(200, 180, 387, 34);

        lblTuoi.setBackground(new java.awt.Color(255, 255, 255));
        lblTuoi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTuoi.setForeground(new java.awt.Color(255, 255, 255));
        lblTuoi.setText("TUỔI");
        getContentPane().add(lblTuoi);
        lblTuoi.setBounds(50, 240, 37, 17);

        txtTuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTuoiActionPerformed(evt);
            }
        });
        getContentPane().add(txtTuoi);
        txtTuoi.setBounds(200, 240, 181, 32);

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("EMAIL");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(50, 290, 46, 17);
        getContentPane().add(txtEmail);
        txtEmail.setBounds(200, 290, 387, 34);

        lblLuong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLuong.setForeground(new java.awt.Color(255, 255, 255));
        lblLuong.setText("LƯƠNG");
        lblLuong.setRequestFocusEnabled(false);
        lblLuong.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(lblLuong);
        lblLuong.setBounds(50, 350, 52, 17);

        txtLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongActionPerformed(evt);
            }
        });
        getContentPane().add(txtLuong);
        txtLuong.setBounds(200, 340, 182, 37);

        tblEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblEmployee.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "TUỔI", "EMAIL", "LƯƠNGl"
            }
        ));
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 470, 761, 160);

        btnNew.setBackground(new java.awt.Color(255, 255, 255));
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/document_add.png"))); // NOI18N
        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew);
        btnNew.setBounds(863, 130, 140, 41);

        btnSave.setBackground(new java.awt.Color(255, 255, 255));
        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/document_download.png"))); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(863, 190, 140, 41);

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/document_delete.png"))); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(863, 250, 140, 41);

        btnFind.setBackground(new java.awt.Color(255, 255, 255));
        btnFind.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/magnifying_glass.png"))); // NOI18N
        btnFind.setText("FIND");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        getContentPane().add(btnFind);
        btnFind.setBounds(863, 310, 140, 41);

        btnOpen.setBackground(new java.awt.Color(255, 255, 255));
        btnOpen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/document.png"))); // NOI18N
        btnOpen.setText("OPEN");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpen);
        btnOpen.setBounds(863, 370, 140, 40);

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/close.png"))); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(863, 430, 140, 41);

        btnFirst.setBackground(new java.awt.Color(255, 255, 255));
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/circle_rewind.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        getContentPane().add(btnFirst);
        btnFirst.setBounds(200, 410, 65, 41);

        btnPrevious.setBackground(new java.awt.Color(255, 255, 255));
        btnPrevious.setForeground(new java.awt.Color(0, 51, 255));
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/arrow_left.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrevious);
        btnPrevious.setBounds(410, 410, 65, 41);

        btnNext.setBackground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/arrow_right.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext);
        btnNext.setBounds(310, 410, 65, 41);

        btnLast.setBackground(new java.awt.Color(255, 255, 255));
        btnLast.setForeground(new java.awt.Color(0, 51, 255));
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/32/circle_fast_forward.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        getContentPane().add(btnLast);
        btnLast.setBounds(520, 410, 65, 41);

        lblStatus.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(255, 255, 255));
        lblStatus.setText("Record : ");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(420, 350, 144, 15);

        btnSortName.setBackground(new java.awt.Color(255, 255, 255));
        btnSortName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSortName.setText("Sort Name");
        btnSortName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortNameActionPerformed(evt);
            }
        });
        getContentPane().add(btnSortName);
        btnSortName.setBounds(865, 600, 140, 43);

        btnSortPrice.setBackground(new java.awt.Color(255, 255, 255));
        btnSortPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSortPrice.setText("Sort Salary");
        btnSortPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortPriceActionPerformed(evt);
            }
        });
        getContentPane().add(btnSortPrice);
        btnSortPrice.setBounds(863, 540, 140, 46);

        btnSortAge.setBackground(new java.awt.Color(255, 255, 255));
        btnSortAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSortAge.setText("Sort Age");
        btnSortAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortAgeActionPerformed(evt);
            }
        });
        getContentPane().add(btnSortAge);
        btnSortAge.setBounds(863, 480, 140, 47);

        lblDongHo.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(255, 0, 51));
        lblDongHo.setText("Time");
        lblDongHo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblDongHoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(lblDongHo);
        lblDongHo.setBounds(880, 90, 123, 27);

        lblImage.setBackground(new java.awt.Color(255, 51, 51));
        lblImage.setForeground(new java.awt.Color(255, 255, 255));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/IKONS/PNG/128/user_add.png"))); // NOI18N
        lblImage.setAlignmentX(5.0F);
        getContentPane().add(lblImage);
        lblImage.setBounds(590, 100, 270, 260);

        btnChoseeImage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChoseeImage.setText("Choose Image");
        btnChoseeImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoseeImageActionPerformed(evt);
            }
        });
        getContentPane().add(btnChoseeImage);
        btnChoseeImage.setBounds(660, 370, 160, 25);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/Images/user_add.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(600, 100, 250, 260);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PS11601_PhongDTN_MOB1023_ASM/backgroundQLNV.jpg"))); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-60, 30, 1077, 621);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:        
        StringBuilder sb = new StringBuilder();
        Validator.checkEmpty(txtMaNhanVien, sb, "Mã nhân viên không được để trống !!!\n");
        Validator.checkEmpty(txtHoVaTen, sb, "Tên nhân viên không được để trống !!!\n");
        Validator.checkAge(txtTuoi, sb);
        Validator.checkEmail(txtEmail, sb);
        Validator.checkSalary(txtLuong, sb);
        if(sb.length()>0){
            JOptionPane.showMessageDialog(this, sb.toString(),"Invalid Data",JOptionPane.ERROR_MESSAGE);
            return ;
        }
        if(btnSave.getText().equalsIgnoreCase("Save")){
            this.addEmployee();
            lblStatus.setText("Record : " + " of "+list.size());
        }else{
            this.updateEmployee();
            lblStatus.setText("Record : " + " of "+list.size());
        }
            this.fillToTable();
            clearForm();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
        lblStatus.setText("Record : " + " of "+list.size());
        tblEmployee.setRowSelectionAllowed(false);
        txtMaNhanVien.enable(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // TODO add your handling code here:
        index = tblEmployee.getSelectedRow();
        btnSave.setText("Update");
        this.showDetail();
        updateStatusRecord();
        txtMaNhanVien.enable(false);
        tblEmployee.setRowSelectionInterval(index,index);
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int question = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát khỏi chương trình và lưu lại dữ liệu không ? ");
        if(question==0){
        saveFile();
        System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index = 0;
        showDetail();
        updateStatusRecord();
        tblEmployee.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        // TODO add your handling code here:
        if(index>0){
            index--;
            showDetail();
            updateStatusRecord();
            tblEmployee.setRowSelectionInterval(index, index);
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index = list.size()-1;
        showDetail();
        updateStatusRecord();
        tblEmployee.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if(index<list.size()-1){
            index++;
            showDetail();
            updateStatusRecord();
            tblEmployee.setRowSelectionInterval(index, index);
        }
            tblEmployee.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        OpenFile();
        if(list.size()>0){
            index = 0;
            showDetail();
            fillToTable();
            updateStatusRecord();
            btnSave.setText("Update");
            tblEmployee.setRowSelectionInterval(index, index);
        }else{
            clearForm();
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
        index = tblEmployee.getSelectedRow();
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa nhân viên ?");
        if(check==0){
            list.remove(index);
            this.clearForm();
            lblStatus.setText("Record : " + " of "+list.size());
            this.fillToTable();    
        }
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        // TODO add your handling code here:
        this.Find();
        tblEmployee.setRowSelectionInterval(index, index);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnSortNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortNameActionPerformed
        // TODO add your handling code here:
        this.sortName();
        this.fillToTable();
    }//GEN-LAST:event_btnSortNameActionPerformed

    private void btnSortPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortPriceActionPerformed
        // TODO add your handling code here:
        this.sortPrice();
        this.fillToTable();
    }//GEN-LAST:event_btnSortPriceActionPerformed

    private void txtMaNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMaNhanVienMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtMaNhanVienMouseClicked

    private void txtMaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhanVienActionPerformed

    private void btnSortAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortAgeActionPerformed
        // TODO add your handling code here:
        this.sortAge();
        this.fillToTable();
    }//GEN-LAST:event_btnSortAgeActionPerformed

    private void txtTuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTuoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTuoiActionPerformed

    private void lblDongHoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblDongHoAncestorAdded
        // TODO add your handling code here:
        this.Clock();
    }//GEN-LAST:event_lblDongHoAncestorAdded

    private void btnChoseeImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoseeImageActionPerformed
        // TODO add your handling code here:
        this.ChooserImage();
    }//GEN-LAST:event_btnChoseeImageActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChoseeImage;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSortAge;
    private javax.swing.JButton btnSortName;
    private javax.swing.JButton btnSortPrice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTuoi;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}

