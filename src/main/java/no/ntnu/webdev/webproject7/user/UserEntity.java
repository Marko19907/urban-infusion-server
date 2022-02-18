package no.ntnu.webdev.webproject7.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
    @Id
    private String id;
    private Boolean admin;
    private String email;
    private String password;

    public UserEntity(String id, Boolean admin, String email, String password) {
        this.id = id;
        this.admin = admin;
        this.email = email;
        this.password = password;
    }

    protected UserEntity() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
