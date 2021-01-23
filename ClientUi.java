package com.brainbooster.bblink.bbplanning.ui;

import java.util.Date;

/**
 * @author TAOUSSET Abdoul
 **/

public class ClientUI {

    private String profession;
    private Date   naissance;
    private String sex;

    public String getProfession() {
        return profession;
    }

    public void setProfession( String profession ) {
        this.profession = profession;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance( Date naissance ) {
        this.naissance = naissance;
    }

    public String getSex() {
        return sex;
    }

    public void setSex( String sex ) {
        this.sex = sex;
    }

}
