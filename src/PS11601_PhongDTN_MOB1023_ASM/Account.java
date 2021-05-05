
package PS11601_PhongDTN_MOB1023_ASM;

import java.io.Serializable;

public class Account implements Serializable{
    public String username;
    public String password;
    boolean isRemember=true;

    public Account() {
    }

    public Account(String username, String password,boolean isRemember) {
        this.username = username;
        this.password = password;
        this.isRemember = isRemember;
    }
    
}
