package com.cleverti.simpletrainticketmachine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cleverti.simpletrainticketmachine.repositories.entities.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
}
