package com.mnp.store.common.configs;

import com.mnp.store.domain.constants.RoleConstants;
import com.mnp.store.domain.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(
            RoleRepository roleRepository
    ) {
        return args -> {
            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(RoleConstants.admin());
                roleRepository.save(RoleConstants.user());
                roleRepository.save(RoleConstants.anonymous());
            }
        };
    }
}
