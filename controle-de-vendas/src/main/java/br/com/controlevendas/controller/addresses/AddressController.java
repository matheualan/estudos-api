package br.com.controlevendas.controller.addresses;

import br.com.controlevendas.dto.AddressDTO;
import br.com.controlevendas.service.addresses.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.saveAddress(addressDTO));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> listAllAddresses() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.listAllAddress());
    }

}
