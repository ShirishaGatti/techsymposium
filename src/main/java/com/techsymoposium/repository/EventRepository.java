package com.techsymoposium.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techsymoposium.model.Event;
public interface EventRepository extends JpaRepository<Event,Integer>{

}
