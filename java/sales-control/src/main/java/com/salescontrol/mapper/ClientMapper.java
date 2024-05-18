package com.salescontrol.mapper;

import com.salescontrol.dto.ClientGetRequestBody;
import com.salescontrol.dto.ClientPostRequestBody;
import com.salescontrol.dto.ClientPutRequestBody;
import com.salescontrol.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public abstract Client toClient(ClientPostRequestBody clientPostRequestBody);
    public abstract Client toClient(ClientPutRequestBody clientPutRequestBody);
    public abstract ClientGetRequestBody toClientGet(Client clientGetRequestBody);

}
