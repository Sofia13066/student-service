package telran.java2022.studentservice;

import java.util.List;

import telran.java2022.dto.ScoreDto;
import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.dto.StudentUpdateDto;

public interface StudentService {
    Boolean addStudent(StudentCreateDto studentCreateDto);
    StudentDto findStudent(Integer id);
    StudentDto removeStudent(Integer id);
    StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);
    Boolean addScore(Integer id, ScoreDto scoreDto);
    List<StudentDto> findStudentByName(String name);
    Long getStudentsNamesQuantity(List<String> names);
    List<StudentDto> getStudentByExamScore(String exam, Integer score);
    
}
