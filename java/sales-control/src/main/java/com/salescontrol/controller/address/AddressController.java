package com.salescontrol.controller.address;

import com.salescontrol.dto.address.AddressPostDTO;
import com.salescontrol.service.AddressService;
import com.salescontrol.service.ViaCepService;
import com.salescontrol.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
@Log4j2
public class AddressController {

    private final AddressService addressService;
    private final DateUtil dateUtil;
    private final ViaCepService viaCepService;

    @PostMapping(value = "/save-address")
    public ResponseEntity<AddressPostDTO> saveAddress(@RequestBody AddressPostDTO addressPostDTO, String name) {
        log.info(dateUtil.dateFormatter(LocalDateTime.now()).concat(" POST saveAddress()"));
        return ResponseEntity.status(HttpStatus.OK).body(addressService.saveAddress(addressPostDTO, name));
    }

    @PostMapping(value = "/save-address-byCep")
    public ResponseEntity<AddressPostDTO> saveAddressByCep(String cep) {
        return ResponseEntity.status(HttpStatus.CREATED).body(viaCepService.buscarCep(cep));
    }

}
