package com.brainbooster.bblink.bbplanning.controllers;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.brainbooster.bblink.bbplanning.entities.Client;
import com.brainbooster.bblink.bbplanning.services.ClientService;

// TODO: Auto-generated Javadoc

/**
 * The Class ClientController.
 * 
 * @author TAOUSSET Abdoul
 */
@Controller
public class ClientController {
    // @Autowired
    // private ClientMapper mapper;

    /** The client service. */
    @Autowired
    private ClientService      clientService;

    /** The Constant VUE_LISTE_CLIENT. */
    public static final String VUE_LISTE_CLIENT  = "CRM/clients";

    /** The Constant VUE_FORM_CLIENT. */
    public static final String VUE_FORM_CLIENT   = "CRM/FormClient";

    /** The Constant VUE_EDIT_CLIENT. */
    public static final String VUE_EDIT_CLIENT   = "CRM/EditClient";

    /** The Constant VUE_IMPORT_CLIENT. */
    public static final String VUE_IMPORT_CLIENT = "CRM/ImportClients";

    /** The Constant VUE_APERCU_CLIENT. */
    public static final String VUE_APERCU_CLIENT = "CRM/ApercuClient";

    /**
     * Index of Clients.
     *
     * @param model
     *            the model
     * @param p
     *            the p(page): numero de page
     * @param s
     *            the s(size): taille page
     * @param mc
     *            the mc(motCle)
     * @param gb
     *            the gb(groubBy): numéro collone table Client
     * @return La vue VUE_LISTE_CLIENT.
     */
    @RequestMapping( value = "/indexClient" )
    public String indexClient( Model model,
            @RequestParam( name = "page", defaultValue = "0" ) int p,
            @RequestParam( name = "size", defaultValue = "5" ) int s,
            @RequestParam( name = "motCle", defaultValue = "" ) String mc,
            @RequestParam( name = "groub", defaultValue = "1" ) int gb ) {
        /**
         * Creation de page et Stockage dans l'objet request.
         * 
         * @see pageCreationClient
         */
        model.addAttribute( "listClients", clientService.pageCreationClient( p, s, mc, gb ).getContent() );
        /**
         * Stockage du tables contanant les pages dans l'objet request.
         * 
         * @see pagesTotalClient
         */
        model.addAttribute( "pages", clientService.pagesTotalClient( p, s, mc, gb ) );
        model.addAttribute( "size", s );
        model.addAttribute( "pageCourante", p );
        model.addAttribute( "motCle", mc );
        model.addAttribute( "groub", gb );
        return VUE_LISTE_CLIENT;
    }

    /**
     * Delete Client.
     *
     * @param id
     *            the id
     * @param motCle
     *            the mot cle
     * @param page
     *            the page: numero de page
     * @param size
     *            the size: taille page
     * @param groub
     *            the groub(grobBy)
     * @return La vue de l'index avec les parametres correspondant.
     */
    @RequestMapping( value = "/deletClient", method = RequestMethod.GET )
    public String deletClient( Long id, String motCle, int page, int size, int groub ) {
        /**
         * Suppression du Client en base dont l'id corrrespond.
         * 
         * @see deleteClient
         */
        clientService.deleteClient( id );
        return "redirect:/indexClient?page=" + page + "&size=" + size + "&motCle=" + motCle + "&groub=" + groub;
    }

    /**
     * Form client.
     *
     * @param model
     *            the model
     * @return La VUE_FORM_CLIENT.
     */
    @RequestMapping( value = "/formClient", method = RequestMethod.GET )
    public String formClient( Model model ) {
        /**
         * Creation et Stockage d'une nouvelle variable entité dans l'objet
         * request.
         */
        model.addAttribute( "client", new Client() );
        return VUE_FORM_CLIENT;
    }

    /**
     * Save Client.
     *
     * @param cl
     *            the cl
     * @param bindingResult
     *            the binding result
     * @return La VUE_FORM_CLIENT s'il ya et erreur ou l'index dans le cas
     *         contraire.
     */
    @RequestMapping( value = "/sauvegardeClient", method = RequestMethod.POST )
    public String sauvegardeClient( @Valid Client cl, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return VUE_FORM_CLIENT;
        }

        /**
         * Enregistrement en base de donne du nouveau Client.
         * 
         * @see saveClient
         */
        clientService.saveClient( cl );

        return "redirect:indexClient";
    }

    /**
     * Edit Client.
     *
     * @param id
     *            the id
     * @param model
     *            the model
     * @return VUE_EDIT_CLIENT
     */
    @RequestMapping( value = "/editClient", method = RequestMethod.GET )
    public String editClient( Long id, Model model ) {
        /**
         * Recuperation du Client en base de donne dont l'id correspond et
         * Stockage dans l'objet request.
         * 
         * @see getClient
         */
        model.addAttribute( "client", clientService.getClient( id ) );
        return VUE_EDIT_CLIENT;
    }

    /**
     * Update Client.
     *
     * @param cl
     *            the cl
     * @param bindingResult
     *            the binding result
     * @return La VUE_EDIT_CLIENT s'il ya et erreur ou l'index dans le cas
     *         contraire
     */
    @RequestMapping( value = "/updateClient", method = RequestMethod.POST )
    public String updateClient( @Valid Client cl, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return VUE_EDIT_CLIENT;
        }

        /** Modification en base de donne du Client. */
        clientService.saveClient( cl );

        return "redirect:indexClient";
    }

    /**
     * Importe client.
     *
     * @param model
     *            the model
     * @param file
     *            the file
     * @return VUE_IMPORT_CLIENT
     * @throws Exception
     *             the exception
     * @throws ParseException
     *             the parse exception
     */
    @RequestMapping( value = "/importClient", method = RequestMethod.POST )
    public String importeClient( Model model, @RequestParam( name = "fichier" ) MultipartFile file )
            throws Exception, ParseException {

        /**
         * Recuperation du fichier recu en parametre, Stockage dans l'objet
         * request et importation
         * 
         * @see importationClient
         */
        model.addAttribute( "resultats", clientService.importationClient( file ) );
        return VUE_IMPORT_CLIENT;
    }

    /**
     * Apercu client.
     *
     * @param id
     *            the id
     * @param model
     *            the model
     * @return the string
     */
    @RequestMapping( value = "/apercuClient", method = RequestMethod.GET )
    public String apercuClient( Long id, Model model ) {
        /**
         * Recuperation du Client en base de donne dont l'id correspond et
         * Stockage dans l'objet request.
         * 
         * @see getClient
         */
        model.addAttribute( "client", clientService.getClient( id ) );
        return VUE_APERCU_CLIENT;
    }

    /**
     * Retour index client.
     *
     * @return the string
     */
    @RequestMapping( value = "/retourClient", method = RequestMethod.POST )
    public String retourIndexClient() {

        return "redirect:indexClient";
    }

}
