package com.zxp.entity.po;

import java.io.Serializable;


public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User otherUser = (User) object;
        if (otherUser.id == this.id) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * this.id.hashCode();
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
