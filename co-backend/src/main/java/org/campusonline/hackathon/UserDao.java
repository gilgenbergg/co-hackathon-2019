package org.campusonline.hackathon;

import java.util.HashMap;
import java.util.Map;

public enum UserDao {
    instance;

    private Map<String, User> logins = new HashMap<>();
    private User[] registeredUsers = new User[100];
    private int index = 0;

    private UserDao() {

        User admin = new User("admin", "root");
        registerUser(admin);

        User user = new User("user", "toor");
        registerUser(user);

    }
    public Map<String, User> getModel(){
        return logins;
    }

    public void registerUser(User u) {
        registeredUsers[index++] = u;
    }

    public User findUser(String logon) {
        for (int i = 0; i < index; i++) {
            if (registeredUsers[i].login.equals(logon) ||
                    registeredUsers[i].email.equals(logon)){
                return registeredUsers[i];
            }
        }
        return null;
    }

    public void rememberLoginToken(String token, User u) {
        logins.put(token, u);
    }

    public User findUserByToken(String token) {
        User u = logins.get(token);
        return u; //caution might be null
    }
}