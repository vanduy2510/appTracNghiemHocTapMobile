package tktsp.example.a17dtha2_aptracnghiem.model;

public class User {
    private String username;
    private String password;
    private String name_user;
    private String email;
    private int permission;

    public User(String username, String password, String name_user, String email, int permission) {
        this.username = username;
        this.password = password;
        this.name_user = name_user;
        this.email = email;
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
