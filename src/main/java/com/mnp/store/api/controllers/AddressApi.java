package com.mnp.store.api.controllers;

import com.mnp.store.contracts.AddressService;
import com.mnp.store.domain.Address;
import com.mnp.store.domain.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressApi {

    private final AddressService addressService;

    public AddressApi(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) throws URISyntaxException {
        if (address.getId() != null) {
            throw new BadRequestException("A new address cannot already have an ID");
        }
        Address result = addressService.save(address);
        return ResponseEntity.created(new URI("/api/addresses/" + result.getId())).body(result);
    }

    @PutMapping("/addresses")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address) throws URISyntaxException {
        if (address.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Address result = addressService.save(address);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        return addressService.findAll();
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        Optional<Address> address = addressService.findOne(id);
        return address.map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


