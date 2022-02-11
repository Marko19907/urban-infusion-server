package no.ntnu.webdev.webproject7.user;

public class User {

    private String id;
    private boolean admin;
    private String email;
    private String password;

    public User(String id, boolean admin, String email, String password) {
        this.id = id;
        this.admin = admin;
        this.email = email;
        this.password = password;
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.admin = false;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
