package ru.testfield.web.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.testfield.web.repository.UserRepository;
import ru.testfield.web.model.cms.Role;
import ru.testfield.web.model.cms.User;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        logger.info("loadUserByUsername username=" + username);

        final User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new BadCredentialsException(username + " not found");
        }

        if(!user.isEnabled()){
            throw new DisabledException("user account disabled");
        }

        return new UserDetails() {

            private static final long serialVersionUID = 2059202961588104658L;

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isEnabled();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isEnabled();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isEnabled();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
                if(user.getRoles()!=null) {
                    for (Role role : user.getRoles()) {
                        auths.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
                    }
                }
                return auths;
            }
        };
    }

}
