package br.com.springboot.essentials2.mapper;

import br.com.springboot.essentials2.dto.ClientPostRequestBodyDTO;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    public abstract Client toClient(ClientPostRequestBodyDTO clientPostRequestBodyDTO);
    public abstract Client toClient(ClientPutRequestBody clientPutRequestBody);
}
