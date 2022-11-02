package telran.java2022.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import telran.java2022.model.Student;


public interface StudentRepository extends CrudRepository<Student, Integer> {
	
	Stream<Student> findByNameIgnoreCase(String name);
	
	long countByNameInIgnoreCase(List<String> names);
}