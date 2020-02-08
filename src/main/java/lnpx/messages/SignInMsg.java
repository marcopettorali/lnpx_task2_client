package lnpx.messages;

import java.io.*;
import java.util.Date;

public class SignInMsg implements Serializable {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public SignInMsg(String username, String password, String email,String fn, String ln,Date dob) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
