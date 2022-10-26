package telran.java2022.studentservice;

import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.dto.StudentUpdateDto;

public interface StudentService {
    Boolean addStudent(StudentCreateDto studentCreateDto);
    StudentDto findStudent(Integer id);
    StudentDto removeStudent(Integer id);
    StudentDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);
    StudentDto[] findStudentByName(String name);
    Integer studentQuantity();
    StudentDto[] findStudentByMinScore(Integer min);
    
}
