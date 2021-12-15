package com.pluralsight.tddjunit5.database;

import java.util.Objects;

public class Credentials {

    private String username;
    private String password;

    public Credentials(String username, String password){
        this.username = username;
        this.password = password;
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
    public boolean equals(Object obj) {
        if( obj == null){
            return false;
        }
        if( obj.getClass() != this.getClass()){
            return false;
        }
        if(this == obj){
            return true;
        }
        Credentials credentials = (Credentials) obj;
        return Objects.equals(username, credentials.getUsername()) && Objects.equals(password, credentials.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
