package br.com.springboot.essentials2.mapper;

import br.com.springboot.essentials2.dto.ClientGetFindById;
import br.com.springboot.essentials2.dto.ClientPostRequestBody;
import br.com.springboot.essentials2.dto.ClientPutRequestBody;
import br.com.springboot.essentials2.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") //Diz ao spring para gerenciar instâncias dessa classe - injeção de dependências
public abstract class ClientMapper {

    public static final ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class); //maneira de chamar os métodos

    public abstract Client toClient(ClientPostRequestBody clientPostRequestBody);
    public abstract Client toClient(ClientPutRequestBody clientPutRequestBody);

    public abstract ClientGetFindById toClientGet(Client client);

}
