package by.intexsoft.study.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "birthday", nullable = false)
    private String birthday;
    @Column(name = "password",unique = true, nullable = false)
    private String password;
    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User(){}

    public User(Long id, String userName, String phoneNumber, String email, String birthday, String password, Boolean status, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public User(String userName, String phoneNumber, String email, String birthday, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    public User(String userName, String phoneNumber, String email, String birthday, String password, Boolean status, List<Role> roles) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public User(String userName, String phoneNumber, String email, String birthday, String password, Boolean status) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.status = status;
    }

    public User(String userName, String phoneNumber, String email, String birthday, String password, List<Role> roles) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}