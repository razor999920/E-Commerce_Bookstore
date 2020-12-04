package com.mnp.store.services;

import com.mnp.store.contracts.AddressService;
import com.mnp.store.domain.Address;
import com.mnp.store.domain.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Address> findOne(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}