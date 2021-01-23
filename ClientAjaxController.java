/**
 * 
 */
package com.brainbooster.bblink.bbplanning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brainbooster.bblink.bbplanning.services.ClientService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientAjaxController personnalisé pour les Clients
 */
@RestController
@RequestMapping( "/ajax/client" )
public class ClientAjaxController {

    /** The client service. */
    @Autowired
    private ClientService clientService;

    /**
     * Methode vérifiant si un email est deja utilise dans la base de donne.
     *
     * @param email
     *            the email
     * @return the response entity
     */
    @GetMapping( "/check-email" )
    public ResponseEntity<Boolean> checkEmailClient( @RequestParam( "email" ) String email ) {
        try {
            final Boolean result = clientService.isEmailClientExist( email );
            if ( Boolean.TRUE.equals( result ) ) {
                return ResponseEntity.ok( result );
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch ( final Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }
    }

    /**
     * Methode vérifiant lors d'une modification sur un Client deja present en
     * base de donne si un email saisie est deja utilise dans la base de donne.
     *
     * @param email
     *            the email
     * @param id
     *            the id
     * @return the response entity
     */
    @GetMapping( "/check-emailup" )
    public ResponseEntity<Boolean> checkEditableEmailClient( @RequestParam( "email" ) String email,
            @RequestParam Long id ) {
        try {
            final Boolean result = clientService.isEmailClientEditable( id, email );
            final Boolean result1 = clientService.isEmailClientExist( email );

            if ( Boolean.FALSE.equals( result ) && Boolean.TRUE.equals( result1 ) ) {
                return ResponseEntity.ok( result );
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch ( final Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }
    }

    /**
     * Methode vérifiant si un numero de telephone est deja utilise dans la base
     * de donne.
     *
     * @param telephone
     *            the telephone
     * @return the response entity
     */
    @GetMapping( "/check-telephone" )
    public ResponseEntity<Boolean> checkTelephoneClient( @RequestParam( "telephone" ) String telephone ) {
        try {
            final Boolean result = clientService.isTelephoneClientExist( telephone );
            if ( Boolean.TRUE.equals( result ) ) {
                return ResponseEntity.ok( result );
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch ( final Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }
    }

    /**
     * Methode vérifiant lors d'une modification sur un Client deja present en
     * base de donne si un telephone saisie est deja utilise dans la base de
     * donne.
     *
     * @param telephone
     *            the telephone
     * @param id
     *            the id
     * @return the response entity
     */
    @GetMapping( "/check-telephoneup" )
    public ResponseEntity<Boolean> checkEditableTelephoneClient( @RequestParam( "telephone" ) String telephone,
            @RequestParam Long id ) {
        try {
            final Boolean result = clientService.isTelephoneClientEditable( id, telephone );
            final Boolean result1 = clientService.isTelephoneClientExist( telephone );

            if ( Boolean.FALSE.equals( result ) && Boolean.TRUE.equals( result1 ) ) {
                return ResponseEntity.ok( result );
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch ( final Exception e ) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }
    }

}
