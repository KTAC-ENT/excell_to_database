/**
 * 
 */
package com.brainbooster.bblink.bbplanning.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.brainbooster.bblink.bbplanning.dto.ClientDto;
import com.brainbooster.bblink.bbplanning.entities.Client;
import com.brainbooster.bblink.bbplanning.ui.ClientUI;

/**
 * @author Abdoul
 *
 */

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring" )
public interface ClientMapper {

    Client toEntity( ClientDto dto );

    ClientDto toDto( Client entity );

    ClientDto toDto( ClientUI ui );

}
