package com.example.rider.service;

import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class RiderDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RiderRepository riderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Rider rider = riderRepository.findByUsername(username);
        if (rider == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(rider.getUsername(), rider.getPassword(), emptyList());
    }
}