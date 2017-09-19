package com.example.a34011_82_10.geotrouvetout.Model;

/**
 * Created by 34011-82-10 on 07/11/2016.
 */

public class NewUser {

    String pseudo;
    String password;

    //***************** CONSTRUCTOR ***************** //

    public NewUser(String pseudo, String password){
        this.pseudo = pseudo;
        this.password = password;
    }

    //***************** GETTERS & SETTERS ***************** //

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
