package br.com.controlevendas.service;

import br.com.controlevendas.dto.AddressDTO;
import br.com.controlevendas.model.Address;
import br.com.controlevendas.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDTO saveAddress(AddressDTO addressDTO) {
        var address = new Address(addressDTO);
        addressRepository.save(address);
        return addressDTO;
    }

    public List<AddressDTO> listAllAddress() {
        List<Address> allAddresses = addressRepository.findAll();
        List<AddressDTO> addressesDTO = new ArrayList<>();

        for (Address address : allAddresses) {
            var addressDTO = new AddressDTO(address);
            addressesDTO.add(addressDTO);
        }

        return addressesDTO;
    }

}
