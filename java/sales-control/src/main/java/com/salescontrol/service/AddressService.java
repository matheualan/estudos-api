package com.salescontrol.service;

import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.mapper.AddressMapper;
import com.salescontrol.model.Address;
import com.salescontrol.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressPostDTO saveAddress(AddressPostDTO addressPostDTO) {
        Address address = AddressMapper.INSTANCE.toAddress(addressPostDTO);
        addressRepository.save(address);
        return addressPostDTO;
    }

}
