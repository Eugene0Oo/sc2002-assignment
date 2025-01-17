package user;
import enums.Role;
import enums.Faculty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class User {

    private String userID;
    private String password; // Storing the plain text password (not recommended for production)
    private Role role;
    private String name;
    private String email;
    private Faculty Faculty;

    // Default constructor with default password
    public User() {
        this.password = "password";
    }

    // Parameterized constructor with dependency injection and default password
    public User(String userID, Role role, String name, String email, Faculty Faculty) {
        this.userID = userID; // Set the userID here
        this.password = "password";
        this.role = role;
        this.name = name;
        this.email = email;
        this.Faculty = Faculty;
    }
    
    public User(String userID, String password, Role role, String name, String email, Faculty Faculty) {
        this.userID = userID; // Set the userID here
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.Faculty = Faculty;
    }

   
    // Getters and setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Faculty getFaculty() {
        return Faculty;
    }

    public void setFaculty(Faculty Faculty) {
        this.Faculty = Faculty;
    }

}
