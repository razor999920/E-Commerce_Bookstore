package com.mnp.store.common.configs;

import com.mnp.store.domain.authorization.RoleConstants;
import com.mnp.store.domain.authorization.RoleRepository;
import com.mnp.store.domain.catalogs.category.CategoryRepository;
import com.mnp.store.domain.orders.OrderStatusConstants;
import com.mnp.store.domain.orders.OrderStatusRepository;
import com.mnp.store.domain.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(
            RoleRepository roleRepository,
            OrderStatusRepository orderStatusRepository,
            UserRepository userRepository
    ) {
        return args -> {
            if (roleRepository.findAll().isEmpty()) {
                roleRepository.save(RoleConstants.admin());
                roleRepository.save(RoleConstants.user());
                roleRepository.save(RoleConstants.anonymous());
            }

            if (orderStatusRepository.findAll().isEmpty()) {
                orderStatusRepository.save(OrderStatusConstants.StatusInCart);
                orderStatusRepository.save(OrderStatusConstants.StatusPaid);
                orderStatusRepository.save(OrderStatusConstants.StatusShipped);
            }


        };
    }
}
