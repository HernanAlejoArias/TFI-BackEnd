package com.kennedy.tfi;

import com.kennedy.tfi.Repositories.UserRepository;
import com.kennedy.tfi.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        /*
         * return new User("foo", "foo", new ArrayList<>()); }
         */

        MyUser myUser = userRepository.findByUsername(userName);
        return new User(myUser.getUsername(), myUser.getPassword(), Arrays.stream(myUser.getRoles().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
