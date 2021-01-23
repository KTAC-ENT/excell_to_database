package com.brainbooster.bblink.bbplanning.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brainbooster.bblink.bbplanning.entities.Client;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClientRepository.
 *
 * @author TAOUSSET Abdoul
 */

public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Chercher Client.
     *
     * @param mc
     *            the mc(motCle)
     * @param gb
     *            the gb(groubBy): numéro collone table Client
     * @param pageable
     *            the pageable
     * @return page contitue de clients presents en base de donne selon la
     *         requete qui suit.
     */
    @Query( "select c from Client c where c.nom like:x or c.profession like:x or c.telephone like:x or c.email like:x or c.ville like:x or c.status like:x or c.entreprise like:x or c.secteur_activite like:x order by :y asc" )
    public Page<Client> chercherClient( @Param( "x" ) String mc, @Param( "y" ) int gb, Pageable pageable );

    /**
     * Find by email.
     *
     * @param email
     *            the email
     * @return the client
     */
    Client findByEmail( String email );

    /**
     * Find by telephone.
     *
     * @param telephone
     *            the telephone
     * @return the client
     */
    Client findByTelephone( String telephone );

    /**
     * Find by id and email.
     *
     * @param id
     *            the id
     * @param email
     *            the email
     * @return the client
     */
    Client findByIdAndEmail( Long id, String email );

    /**
     * Find by id and telephone.
     *
     * @param id
     *            the id
     * @param telephone
     *            the telephone
     * @return the client
     */
    Client findByIdAndTelephone( Long id, String telephone );

}
