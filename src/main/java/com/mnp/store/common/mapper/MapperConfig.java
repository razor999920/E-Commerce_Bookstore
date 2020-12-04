package com.mnp.store.common.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mnp.store.domain.User;
import com.mnp.store.contracts.users.dtos.UserResponseDto;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //applyUserMappings(modelMapper);
        return modelMapper;
    }

    private void applyUserMappings(ModelMapper modelMapper) {
        modelMapper.typeMap(UserResponseDto.class, User.class).addMappings(mapper -> {
            mapper.map(src -> src.getUsername().toLowerCase(), User::setUsername);
            mapper.map(src -> src.getEmail().toLowerCase(), User::setEmail);
        });
    }
}