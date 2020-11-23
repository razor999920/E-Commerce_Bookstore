package com.mnp.store.common.security;

import com.mnp.store.common.security.exceptions.UserNotActivatedException;
import com.mnp.store.domain.users.User;
import com.mnp.store.domain.users.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String usernameOrEmail) throws UsernameNotFoundException {

        boolean isEmail = new EmailValidator().isValid(usernameOrEmail, null);

        Optional<User> user = isEmail
                ? userRepository.findOneByEmailIgnoreCase(usernameOrEmail)
                : userRepository.findOneByUsernameIgnoreCase(usernameOrEmail);

        return user.map(this::createPrincipal)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' does not exist", usernameOrEmail)));
    }

    private org.springframework.security.core.userdetails.User createPrincipal(User user) {
        if (!user.isActivated())
            throw new UserNotActivatedException(String.format("User '%s' is not activated.", user.getUsername()));

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
    }
}
