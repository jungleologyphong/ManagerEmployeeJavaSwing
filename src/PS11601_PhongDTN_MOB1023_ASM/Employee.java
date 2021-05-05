
package PS11601_PhongDTN_MOB1023_ASM;

import java.awt.Image;
import java.io.Serializable;

public class Employee implements Serializable{
    String maNV;
    String hoTen;
    int tuoi;
    String eMail;
    double luong;
    String IMG;
    public Employee(){
    }

    public Employee(String maNV, String hoTen, int tuoi, String eMail, double luong,String IMG) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.eMail = eMail;
        this.luong = luong;
        this.IMG = IMG;
    }
}
