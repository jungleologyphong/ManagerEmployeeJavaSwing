package PS11601_PhongDTN_MOB1023_ASM;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

public class Validator {
    public static boolean checkEmpty(JTextField field, StringBuilder ab,String msg){
       boolean ok = true;
        if(field.getText().equals("")){
           ab.append(msg).append("\n");
           field.setBackground(Color.yellow);
           ok=false;
       }else{
            field.setBackground(Color.white);
        } 
        return ok;
    }
    public static boolean checkAge(JTextField field, StringBuilder sb){
        boolean ok = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập tuổi nhân viên !!!\n")){
            return false;
        }
        try {
            int tuoi = Integer.parseInt(field.getText());
            if(tuoi<16 || tuoi >55){
                sb.append("Bạn đã nhập tuổi nhân viên không hợp lệ tuổi từ 16-55 !!!\n");
                field.setBackground(Color.yellow);
                ok = false;
                
            }
        } catch (Exception e) {
            sb.append("Giá trị tuổi không hợp lệ Phải là giá trị số !!!\n");
            field.setBackground(Color.white);
            ok = false;
        }
        if(ok){
            field.setBackground(Color.white);
        }
        return ok;
    }
    public static boolean checkSalary(JTextField field,StringBuilder sb){
        boolean ok = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập lương nhân viên !!!\n")){
            return false;
        }
        try {
            double salary = Double.parseDouble(field.getText());
            if(salary < 5000000){
                sb.append("Bạn đã nhập lương nhân viên không hợp lệ Phải lớn hơn bằng 5.000.000 VND !!!\n");
                field.setBackground(Color.yellow);
                ok = false;
                
            }
        } catch (Exception e) {
            sb.append("Giá trị lương không hợp lệ Phải là giá trị số !!!\n");
            field.setBackground(Color.white);
            ok = false;
        }
        if(ok){
            field.setBackground(Color.white);
        }
        return ok;
    }
    public static boolean checkEmail(JTextField field,StringBuilder sb){
        boolean ok = true;
        if(!checkEmpty(field, sb, "Bạn chưa nhập Email nhân viên !!!\n")){
            return false;
        }
            Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w+");
            Matcher matcher = pattern.matcher(field.getText());
            if(!matcher.find()){
                sb.append("Email không hợp lệ !!!\n");
                field.setBackground(Color.yellow);
                ok = false;
            }
        if(ok){
            field.setBackground(Color.white);
        }
        return ok;
    }
}
