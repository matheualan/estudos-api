package com.salescontrol.mapper;

import com.salescontrol.dto.client.ClientGetDTO;
import com.salescontrol.dto.client.ClientPostDTO;
import com.salescontrol.dto.client.ClientPutDTO;
import com.salescontrol.dto.client.ClientWithOrderGetDTO;
import com.salescontrol.dto.client.ClientWithOrderPostDTO;
import com.salescontrol.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public abstract Client toClient(ClientPostDTO clientPostDTO);
    public abstract Client toClient(ClientWithOrderPostDTO clientWithOrderPostDTO);
    public abstract Client toClient(ClientPutDTO clientPutDTO);

    public abstract ClientGetDTO toClientGet(Client client);
    public abstract ClientWithOrderGetDTO toClientWithOrderGet(Client client);

}
