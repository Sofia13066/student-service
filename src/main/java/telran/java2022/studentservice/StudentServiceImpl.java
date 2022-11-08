package telran.java2022.studentservice;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.dto.ScoreDto;
import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.dto.StudentUpdateDto;
import telran.java2022.dto.ecxeptions.StudentNotFoundException;
import telran.java2022.model.Student;
import telran.java2022.dao.StudentRepository;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	final StudentRepository studentRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		if(studentRepository.findById(studentCreateDto.getId()).isPresent()) {
			return false;
		}
		// Student student = new Student(studentCreateDto.getId(), 
		// 		studentCreateDto.getName(), studentCreateDto.getPassword());
		Student student = modelMapper.map(studentCreateDto, Student.class);
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto removeStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return null;
		}		
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return null;
		}
		
		student.setName(studentUpdateDto.getName());
		student.setPassword(studentUpdateDto.getPassword());
		studentRepository.save(student);
		return modelMapper.map(student, StudentCreateDto.class);
	}

	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		
		boolean isAdded = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return isAdded;
	}


	@Override
	public Long getStudentsNamesQuantity(List<String> names) {
		return studentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDto> getStudentByExamScore(String exam, Integer score) {
		return studentRepository.findByExamAndScoreGreaterThan(exam, score)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

    @Override
    public List<StudentDto> findStudentByName(String name) {
        return studentRepository.findByNameIgnoreCase(name)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
    }

}

