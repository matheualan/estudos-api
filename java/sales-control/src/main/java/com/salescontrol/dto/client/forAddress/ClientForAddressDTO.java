package com.salescontrol.dto.client.forAddress;

import com.salescontrol.dto.address.AddressPostDTO;
import lombok.Data;

import java.util.List;

@Data
public class ClientForAddressDTO {

    private String name;
    private String cpf;
    private List<AddressPostDTO> addresses;

}
