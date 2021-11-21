package com.example.persist.repo;

import com.example.persist.entity.Device;
import com.example.persist.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository <Event,Integer>{


}
