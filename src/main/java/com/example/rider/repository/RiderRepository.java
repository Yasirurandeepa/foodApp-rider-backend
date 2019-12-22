package com.example.rider.repository;

import com.example.rider.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Rider findByUsername (String Username);
}