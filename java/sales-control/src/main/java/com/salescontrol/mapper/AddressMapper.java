package com.salescontrol.mapper;

import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract Address toAddress(AddressPostDTO addressPostDTO);

}
