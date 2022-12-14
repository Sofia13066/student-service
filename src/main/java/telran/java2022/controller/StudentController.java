package telran.java2022.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.dto.ScoreDto;
import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.dto.StudentUpdateDto;
import telran.java2022.studentservice.StudentService;


@RestController
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;

  @PostMapping("/student")
  public Boolean addStudent(@RequestBody StudentCreateDto studentCreateDto) {
    return studentService.addStudent(studentCreateDto);
  }

  @GetMapping("/student/{id}")
  public StudentDto findStudent(@PathVariable Integer id) {
    return studentService.findStudent(id);
  }

  @DeleteMapping("/student/{id}")
  public StudentDto removeStudent(@PathVariable Integer id) {
    return studentService.removeStudent(id);
  }

  @PutMapping("/student/{id}")
  public StudentCreateDto editStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
    return studentService.updateStudent(id, studentUpdateDto);
  }

  @PutMapping("/score/student/{id}")
  public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
    return studentService.addScore(id, scoreDto);
  }

  @GetMapping("/students/name/{name}")
  public List<StudentDto> findStudentsByName(@PathVariable String name) {
    return studentService.findStudentByName(name);
  }

  @PostMapping("/quantity/students")
  public long studentsNamesQuantity(@RequestBody List<String> names) {
    return studentService.getStudentsNamesQuantity(names);
  }

  @GetMapping("/students/exam/{exam}/minscore/{score}")
  public List<StudentDto> studentsByExamScore(@PathVariable String exam, @PathVariable int score) {
    return studentService.getStudentByExamScore(exam, score);
  }
}
