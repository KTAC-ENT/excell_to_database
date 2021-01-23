/**
 * 
 */
package com.brainbooster.bblink.bbplanning.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.brainbooster.bblink.bbplanning.entities.Client;
import com.brainbooster.bblink.bbplanning.repositories.ClientRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientService.
 *
 * @author TAOUSSET Abdoul
 */

@Service
public class ClientService {

    /** The client repository. */
    @Autowired
    private ClientRepository clientRepository;

    /** The fichier dir. */
    @Value( "${dir.fichier}" )
    private String           fichierDir;

    /**
     * Page creation Client.
     *
     * @param page
     *            the page : numero de page
     * @param size
     *            the size : taille de page
     * @param motCle
     *            the mot cle
     * @param groubBy
     *            the groub by : numero collone table Client
     * @return the page selon la methode chercherClient.
     * 
     * @see chercherClient
     */
    public Page<Client> pageCreationClient( int page, int size, String motCle, int groubBy ) {
        /**
         * Creation d'une nouvelle page avec un numero de page et un taille
         * fixe.
         */
        Pageable PageWidthElements = PageRequest.of( page, size );
        /**
         * Creation d'une page de clients selon la méthode chercherClient
         * définit dans l'interface ClientRepository.
         */
        Page<Client> pageClients = clientRepository.chercherClient( "%" + motCle + "%", groubBy, PageWidthElements );
        return pageClients;
    }

    /**
     * Pages total Client.
     *
     * @param page
     *            the page : numero de page
     * @param size
     *            the size : taille de page
     * @param motCle
     *            the mot cle
     * @param groubBy
     *            the groub by : numero collone table Client
     * @return the int[] : tableau constitué de la totalité des pages crées à
     *         partir des Clients present en base de données grace à la methode
     *         pageCreationClient.
     * @see pageCreationClient
     */
    public int[] pagesTotalClient( int page, int size, String motCle, int groubBy ) {
        int[] pages = new int[pageCreationClient( page, size, motCle, groubBy ).getTotalPages()];
        return pages;
    }

    /**
     * Methode qui supprime un client en base de donne selon l'id correspondant.
     *
     * @param id
     *            the id
     */
    public void deleteClient( Long id ) {
        clientRepository.deleteById( id );
    }

    /**
     * Methode qui enregistre en base de donné un client.
     *
     * @param cl
     *            the cl
     */
    public void saveClient( Client cl ) {
        clientRepository.save( cl );
    }

    /**
     * Methode qui retourne ce client en base de donne a qui correspond cet id.
     *
     * @param id
     *            the id
     * @return client
     */
    public Client getClient( Long id ) {
        return clientRepository.getOne( id );
    }

    /**
     * Checks if is email client exist.
     *
     * @param email
     *            the email
     * @return true, if is email exist
     */
    public boolean isEmailClientExist( String email ) {
        return Objects.nonNull( clientRepository.findByEmail( email ) );
    }

    /**
     * Checks if is email client editable.
     *
     * @param id
     *            the id
     * @param email
     *            the email
     * @return true, if is email editable
     */
    public boolean isEmailClientEditable( Long id, String email ) {
        return Objects.nonNull( clientRepository.findByIdAndEmail( id, email ) );
    }

    /**
     * Checks if is telephone client exist.
     *
     * @param telephone
     *            the telephone
     * @return true, if is telephone exist
     */
    public boolean isTelephoneClientExist( String telephone ) {
        return Objects.nonNull( clientRepository.findByTelephone( telephone ) );
    }

    /**
     * Checks if is telephone client editable.
     *
     * @param id
     *            the id
     * @param telephone
     *            the telephone
     * @return true, if is telephone editable
     */
    public boolean isTelephoneClientEditable( Long id, String telephone ) {
        return Objects.nonNull( clientRepository.findByIdAndTelephone( id, telephone ) );
    }

    /**
     * Methode qui permet d'importer des clients a partir d'un tableur sous
     * format XLS.
     *
     * @param file
     *            the file
     * @return the string : signalant les erreurs lors de l'importation ou le
     *         succes de l'importation.
     * @throws Exception
     *             the exception
     * @throws ParseException
     *             the parse exception
     */
    public List<String> importationClient( MultipartFile file ) throws Exception, ParseException {
        String resultat = "";
        List<String> Allresultats = new ArrayList<>();
        String namefile = null;
        if ( !( file.isEmpty() ) ) {
            /**
             * Recuperation du nom du fichier dans la chaine de caractere
             * namefile.
             */
            namefile = file.getOriginalFilename();
            /**
             * Recuperation du repertoire d'envoie du fichier et le nom du
             * fichier dans la chaine de caractere fileName.
             */
            String fileName = fichierDir + namefile;
            /**
             * Envoie du fichier dans le repertoire correspondant sous le meme
             * nom.
             */
            file.transferTo( new File( fileName ) );
            try {
                /**
                 * Creation d'une liste de chaines de caracteres pour recuperer
                 * les lignes(Clients) contenu dans le tableur.
                 */
                List<String> fileLines = Files.lines( Paths.get( fileName ) ).collect( Collectors.toList() );
                int index = 0;
                /**
                 * Boucle pour parcourir la totalite des lignes presentent dans
                 * le tableur XLS.
                 */
                for ( String line : fileLines ) {
                    /**
                     * Definition de la tabulation comme separateur d'attributs
                     * dans une ligne car c'est la cas pour une fichier XLS.
                     */
                    String[] lineSplit = line.split( "\\t" );
                    /**
                     * Utilisation de la condition if(index !=0) pour ne pas
                     * effectuer d'operations sur la premiere ligne du tableur
                     * car la premiere ligne contient les titres des attibuts.
                     */
                    if ( index != 0 ) {
                        /**
                         * Recuperation du nombre d'attributs present dans la
                         * ligne courante dans l'entier taille.
                         */
                        int taille = lineSplit.length;
                        /**
                         * Creation des chaines de caracteres qui vont récupérer
                         * les attributs de la ligne courante.
                         */
                        String nom = "";
                        String telephone = "";
                        String email = "";
                        String ville = "";
                        String profession = "";
                        String entreprise = "";
                        String activite = "";
                        String date = "";
                        String sex = "";
                        String status = "";
                        /**
                         * Definition d'un format de date dans la variable sdf
                         * correspondant au format de date du tableur XLS.
                         */
                        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
                        Date dt = null;
                        /**
                         * Recuperation des attributs de la ligne courante dans
                         * des chaines de caracteres si ces attributs existent
                         * dans la ligne courante.
                         */
                        if ( taille >= 1 ) {
                            nom = lineSplit[0];
                        }
                        if ( taille >= 2 ) {
                            telephone = lineSplit[1];
                        }
                        if ( taille >= 3 ) {
                            email = lineSplit[2];
                        }
                        if ( taille >= 4 ) {
                            ville = lineSplit[3];
                        }
                        if ( taille >= 5 ) {
                            profession = lineSplit[4];
                        }
                        if ( taille >= 6 ) {
                            entreprise = lineSplit[5];
                        }
                        if ( taille >= 7 ) {
                            activite = lineSplit[6];
                        }
                        if ( taille >= 8 ) {
                            date = lineSplit[7];
                            /**
                             * Remplacement des caracteres '-' par les
                             * caracteres '/' dans la chaine de caratere date
                             * car des erreurs peuvent subvenir.
                             */
                            date = date.replace( "-", "/" );
                            /**
                             * Conversion de la chaine de caractere date en
                             * variable de type date et recuperation dans la
                             * variable dt si le format de la date est conforme.
                             */
                            try {
                                dt = Objects.isNull( date ) || date.isEmpty() ? null : sdf.parse( date );
                            } catch ( ParseException ep ) {
                                date = "erreur";
                            }
                        }
                        if ( taille >= 9 ) {
                            sex = lineSplit[8];
                        }
                        if ( taille >= 10 ) {
                            status = lineSplit[9];
                        }
                        /**
                         * Creation d'une variable cl de type Client auquel on
                         * affecte les bons attributs correspondant à la ligne
                         * courante.
                         */
                        Client cl = new Client( nom,
                                telephone,
                                email,
                                ville,
                                profession,
                                entreprise,
                                activite,
                                dt,
                                sex,
                                status );

                        /**
                         * Tests de validations sur les attributs de client.
                         * 
                         * @see UtilitaireService
                         */
                        if ( validationTelephone( telephone ) == true
                                && validationEmail( email ) == true
                                && validationLongChamp( nom, 30 ) == true
                                && validationContain( nom ) == true
                                && validationDate( date ) == true
                                && validationLongChamp( telephone, 30 ) == true
                                && validationLongChamp( email, 255 ) == true
                                && validationLongChamp( ville, 30 ) == true
                                && validationLongChamp( profession, 30 ) == true
                                && validationLongChamp( entreprise, 30 ) == true
                                && validationLongChamp( activite, 30 ) == true
                                && validationSex( sex ) == true
                                && validationContain( sex ) == true
                                && validationLongChamp( status, 30 ) == true ) {
                            if ( clientRepository.findByEmail( email ) == null
                                    && clientRepository.findByTelephone( telephone ) == null ) {
                                /**
                                 * Enregistrement en base de donné du nouveau
                                 * Client si aucune erreur n'est survenue et si
                                 * le client n'existe pas encore.
                                 */
                                clientRepository.save( cl );
                            }
                        } else if ( validationClientTableur( cl ) == true ) {
                            /**
                             * Incrémentation dans la chaine de caractere
                             * resultat des valeurs des lignes ayant rencontrés
                             * des erreurs lors des tests sur les attributs.
                             */
                            resultat = "Échec de l'importation du client Numéro " + "\t" + ( index + 1 )
                                    + "\t" + nom
                                    + "\t" + telephone
                                    + "\t" + email
                                    + "\t" + ville
                                    + "\t" + profession
                                    + "\t" + entreprise
                                    + "\t" + activite
                                    + "\t" + date
                                    + "\t" + sex
                                    + "\t" + status;
                            Allresultats.add( resultat );
                        }
                    }
                    index++;
                }
            } catch ( Exception e ) {
                /**
                 * Supression du fichier en cas d'erreur rencontré lors de
                 * l'importation.
                 */
                Files.delete( Paths.get( fileName ) );
                e.printStackTrace();
            }
            if ( resultat == "" ) {
                /** Si l'importation ne rencontre aucunes erreurs. */
                resultat = "Importation réussite!";
                Allresultats.add( resultat );
            }
            Files.delete( Paths.get( fileName ) );
        } else {
            /** Si aucun fichier n'est envoyé par l'utilisateur. */
            resultat = "Nous n'avez pas choisi votre fichier avant de faire l'importation!";
            Allresultats.add( resultat );
        }

        return Allresultats;
    }

    /**
     * Validation sex.
     *
     * @param sex
     *            the sex
     * @return the boolean : vrai si la chaine sex coorespond aux caracteres 'H'
     *         ou 'F', ou faux sinon.
     */
    private Boolean validationSex( String sex ) {
        if ( Objects.equals( sex, new String( "H" ) ) || Objects.equals( sex, new String( "F" ) ) ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validation client tableur.
     *
     * @param cl
     *            the cl
     * @return the boolean : vrai si au moins un des attributs du client en
     *         parametre n'est pas vide et faux sinon.
     */
    public Boolean validationClientTableur( Client cl ) {
        if ( cl.getNom() != "" || cl.getTelephone() != "" || cl.getEmail() != "" || cl.getVille() != ""
                || cl.getProfession() != "" || cl.getEntreprise() != ""
                || cl.getSecteur_activite() != "" || cl.getNaissance() != null || cl.getSex() != ""
                || cl.getStatus() != "" ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validation telephone.
     *
     * @param telephone
     *            the telephone
     * @return the boolean : faux si la chaine telephone est vide ou si elle
     *         n'est pas null et n'est pas constué uniquement de caracteres type
     *         digital, ou vrai sinon.
     */
    public Boolean validationTelephone( String telephone ) {
        if ( ( telephone != null && !telephone.matches( "^\\d+$" ) ) || telephone.isEmpty() ) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Validation email.
     *
     * @param email
     *            the email
     * @return the boolean : faux si la chaine email est vide ou si elle n'est
     *         pas null et ne respecte pas la syntaxe general d'un email, ou
     *         vrai sinon.
     */
    public Boolean validationEmail( String email ) {
        if ( ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) || email.isEmpty() ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validation long champ.
     *
     * @param mot
     *            the mot
     * @param max
     *            the max
     * @return the boolean : faux si la taille de la chaine mot est supérieur a
     *         l'entier max, ou vrai sinon.
     */
    public Boolean validationLongChamp( String mot, int max ) {
        if ( mot.length() > max ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validation contain.
     *
     * @param mot
     *            the mot
     * @return the boolean : faux si la chaine mot est null ou vide, ou vrai
     *         sinon.
     */
    public Boolean validationContain( String mot ) {
        if ( mot.isEmpty() || Objects.isNull( mot ) ) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Validation date.
     *
     * @param date
     *            the date
     * @return the boolean : faux si la chaine date correspond "erreur" ou vrai
     *         sinon.
     */
    private Boolean validationDate( String date ) {
        if ( Objects.equals( date, new String( "erreur" ) ) ) {
            return false;
        } else {
            return true;
        }
    }

}
