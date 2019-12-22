package com.example.rider.controller;

import com.example.rider.exception.ResourceNotFoundException;
import com.example.rider.model.Rider;
import com.example.rider.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RiderController {

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users")
    public Page<Rider> getUsers(Pageable pageable) {
        return riderRepository.findAll(pageable);
    }


    @PostMapping("/users/sign-up")
    public Rider createUser(@Valid @RequestBody Rider rider) {
        rider.setPassword(bCryptPasswordEncoder.encode(rider.getPassword()));
        return riderRepository.save(rider);
    }

    @PutMapping("/users/{userId}")
    public Rider updateUser(@PathVariable Long userId,
                            @Valid @RequestBody Rider riderRequest) {
        return riderRepository.findById(userId)
                .map(user -> {
                    user.setEmail(riderRequest.getEmail());
                    user.setPhone(riderRequest.getPhone());
                    user.setUsername(riderRequest.getUsername());
                    return riderRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return riderRepository.findById(userId)
                .map(user -> {
                    riderRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + userId));
    }
}