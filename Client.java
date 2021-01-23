package com.brainbooster.bblink.bbplanning.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class Client.
 *
 * @author TAOUSSET Abdoul
 */
@Entity
public class Client {

    /**
     * L'attribut id est auto généré dans la base de donné avec la peristence
     * jpa.
     */
    @Id
    @GeneratedValue
    private Long   id;

    /**
     * L'attribut nom a pour restriction de ne pas etre vide et d'avoir une
     * taille maximale de 30.
     */
    @NotEmpty
    @Column( length = 30 )
    @Size( max = 30 )
    private String nom;

    /**
     * L'attribut telephone a pour restriction d'avoir une taille maximale de 30
     * et de ne contenir que caractères de type digital.
     */
    @Column( length = 30 )
    @Size( max = 30 )
    @Pattern( regexp = "^\\d+$", message = "Saisir uniquement des nombres" )
    private String telephone;

    /**
     * L'attribut email a pour restriction de ne pas etre vide, d'avoir une
     * taille maximale de 255 et de respecter la syntaxe générale d'un email.
     */
    @NotEmpty
    @Size( max = 255 )
    @Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "doit être une adresse email bien formée" )
    private String email;

    /**
     * L'attribut ville a pour restriction d'avoir une taille maximale de 30.
     */
    @Column( length = 30 )
    @Size( max = 30 )
    private String ville;

    /**
     * L'attribut profession a pour restriction d'avoir une taille maximale de
     * 30 .
     */
    @Column( length = 30 )
    @Size( max = 30 )
    private String profession;

    /**
     * L'attribut entreprise a pour restriction d'avoir une taille maximale de
     * 30 .
     */
    @Column( length = 30 )
    @Size( max = 30 )
    private String entreprise;

    /**
     * L'attribut secteur_activite a pour restriction de ne pas etre vide et
     * d'avoir une taille maximale de 30.
     */
    @Column( length = 30 )
    @Size( max = 30 )
    private String secteur_activite;

    /**
     * L'attribut naissance a pour restriction de respecter la syntaxe du format
     * date.
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date   naissance;

    /**
     * L'attribut sex a pour restriction de ne pas etre vide et d'avoir une
     * taille maximale de 3.
     */
    @NotEmpty
    @Column( length = 3 )
    private String sex;

    /**
     * L'attribut satus a pour restriction d'avoir une taille maximale de 30.
     */
    @Column( length = 30 )
    @Size( max = 30 )
    private String status;

    /**
     * L'attribut comentaire a pour restriction d'avoir une taille maximale de
     * 255.
     */
    @Size( max = 255 )
    private String commentaire;

    /** Constructeur vide. */
    public Client() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Instantiates a new client with commentaire.
     *
     * @param nom
     *            the nom
     * @param telephone
     *            the telephone
     * @param email
     *            the email
     * @param ville
     *            the ville
     * @param profession
     *            the profession
     * @param entreprise
     *            the entreprise
     * @param secteur_activite
     *            the secteur activite
     * @param naissance
     *            the naissance
     * @param sex
     *            the sex
     * @param status
     *            the status
     * @param commentaire
     *            the commentaire
     */
    public Client( @NotEmpty @Size( max = 30 ) String nom,
            @Size( max = 30 ) @Pattern( regexp = "^\\d+$", message = "Saisir uniquement des nombres" ) String telephone,
            @NotEmpty @Size( max = 255 ) @Email String email, @Size( max = 30 ) String ville,
            @Size( max = 30 ) String profession, @Size( max = 30 ) String entreprise,
            @Size( max = 30 ) String secteur_activite, Date naissance, @NotEmpty String sex,
            @Size( max = 30 ) String status, @Size( max = 255 ) String commentaire ) {
        super();
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.ville = ville;
        this.profession = profession;
        this.entreprise = entreprise;
        this.secteur_activite = secteur_activite;
        this.naissance = naissance;
        this.sex = sex;
        this.status = status;
        this.commentaire = commentaire;
    }

    /**
     * Instantiates a new client without commentaire.
     *
     * @param nom
     *            the nom
     * @param telephone
     *            the telephone
     * @param email
     *            the email
     * @param ville
     *            the ville
     * @param profession
     *            the profession
     * @param entreprise
     *            the entreprise
     * @param secteur_activite
     *            the secteur activite
     * @param naissance
     *            the naissance
     * @param sex
     *            the sex
     * @param status
     *            the status
     */
    public Client( @NotEmpty @Size( max = 30 ) String nom,
            @Size( max = 30 ) @Pattern( regexp = "^\\d+$", message = "Saisir uniquement des nombres" ) String telephone,
            @NotEmpty @Size( max = 255 ) @Email String email, @Size( max = 30 ) String ville,
            @Size( max = 30 ) String profession, @Size( max = 30 ) String entreprise,
            @Size( max = 30 ) String secteur_activite, Date naissance, @NotEmpty String sex,
            @Size( max = 30 ) String status ) {
        super();
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.ville = ville;
        this.profession = profession;
        this.entreprise = entreprise;
        this.secteur_activite = secteur_activite;
        this.naissance = naissance;
        this.sex = sex;
        this.status = status;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId( Long id ) {
        this.id = id;
    }

    /**
     * Gets the nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the nom.
     *
     * @param nom
     *            the new nom
     */
    public void setNom( String nom ) {
        this.nom = nom;
    }

    /**
     * Gets the telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the telephone.
     *
     * @param telephone
     *            the new telephone
     */
    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail( String email ) {
        this.email = email;
    }

    /**
     * Gets the ville.
     *
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Sets the ville.
     *
     * @param ville
     *            the new ville
     */
    public void setVille( String ville ) {
        this.ville = ville;
    }

    /**
     * Gets the profession.
     *
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Sets the profession.
     *
     * @param profession
     *            the new profession
     */
    public void setProfession( String profession ) {
        this.profession = profession;
    }

    /**
     * Gets the entreprise.
     *
     * @return the entreprise
     */
    public String getEntreprise() {
        return entreprise;
    }

    /**
     * Sets the entreprise.
     *
     * @param entreprise
     *            the new entreprise
     */
    public void setEntreprise( String entreprise ) {
        this.entreprise = entreprise;
    }

    /**
     * Gets the secteur activite.
     *
     * @return the secteur activite
     */
    public String getSecteur_activite() {
        return secteur_activite;
    }

    /**
     * Sets the secteur activite.
     *
     * @param secteur_activite
     *            the new secteur activite
     */
    public void setSecteur_activite( String secteur_activite ) {
        this.secteur_activite = secteur_activite;
    }

    /**
     * Gets the naissance.
     *
     * @return the naissance
     */
    public Date getNaissance() {
        return naissance;
    }

    /**
     * Sets the naissance.
     *
     * @param naissance
     *            the new naissance
     */
    public void setNaissance( Date naissance ) {
        this.naissance = naissance;
    }

    /**
     * Gets the sex.
     *
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex.
     *
     * @param sex
     *            the new sex
     */
    public void setSex( String sex ) {
        this.sex = sex;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the new status
     */
    public void setStatus( String status ) {
        this.status = status;
    }

    /**
     * Gets the commentaire.
     *
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Sets the commentaire.
     *
     * @param commentaire
     *            the new commentaire
     */
    public void setCommentaire( String commentaire ) {
        this.commentaire = commentaire;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Client [id=" + id + ", nom=" + nom + ", telephone=" + telephone + ", email=" + email + ", ville="
                + ville + ", profession=" + profession + ", entreprise=" + entreprise + ", secteur_activite="
                + secteur_activite + ", naissance=" + naissance + ", sex=" + sex + ", status=" + status
                + ", commentaire=" + commentaire + "]";
    }

}
