
package com.techsymoposium.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techsymoposium.model.Student;
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
