package com.salescontrol.service;

import com.salescontrol.dto.AddressPostDTO;
import com.salescontrol.model.Address;
import com.salescontrol.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private String cep;
    private final String urlViaCep = "viacep.com.br/ws/cep/json/";

    public Address saveAddress(AddressPostDTO addressPostDTO) {

        return null;
    }

}
