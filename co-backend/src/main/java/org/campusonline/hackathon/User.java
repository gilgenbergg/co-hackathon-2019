package org.campusonline.hackathon;



public class User {
    public String login;
    public String email;
    public String password; // in cleartext wohoo

    public User (String login, String password) {
        this.login = login;
        this.password = password;
        this.email = "";
    }
    User(String l, String p, String e) {
        this.login = l;
        this.password = p;
        this.email = e;
    }
}
