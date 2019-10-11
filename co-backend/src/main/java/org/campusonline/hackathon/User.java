package org.campusonline.hackathon;

public class User {
    public String logon;
    public String email;
    public String password; // in cleartext wohoo

    public User (String logon, String password) {
        this.logon = logon;
        this.password = password;
    }
}
