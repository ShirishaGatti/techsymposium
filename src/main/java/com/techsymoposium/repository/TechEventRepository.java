package com.techsymoposium.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techsymoposium.model.Student;
import com.techsymoposium.model.TechEvent;

public interface TechEventRepository extends JpaRepository<TechEvent,Integer>{

}
