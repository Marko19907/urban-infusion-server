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

    private String getId() {
        return this.id;
    }

    private void setId(String id) {
        this.id = id;
    }

    private boolean isAdmin() {
        return this.admin;
    }

    private void setAdmin(boolean admin) {
        this.admin = admin;
    }

    private String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private String getPassword() {
        return this.password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
