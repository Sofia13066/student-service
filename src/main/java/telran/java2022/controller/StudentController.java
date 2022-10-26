package telran.java2022.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.studentservice.StudentService;


@RestController
public class StudentController {
    StudentService studentService;

    @PostMapping("/student")
    public boolean addStudent(@RequestBody StudentCreateDto studentCreateDto){
        return studentService.addStudent(studentCreateDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Integer id){
        return studentService.findStudent(id);
    }

    @GetMapping("/students/name/{name}")
    public StudentDto[] findStudentByName(@PathVariable String name){
        return studentService.findStudentByName(name);
    }

    @PostMapping("quantity/students")
    public int quantity(){
        int studentQuantity = 0;
        return studentQuantity;
    }

    @GetMapping("/students/exam/History/minscore/{min}")
    public StudentDto[] findStudentByMinScore(@PathVariable Integer min){
        return studentService.findStudentByMinScore(min);
    }
    
}
