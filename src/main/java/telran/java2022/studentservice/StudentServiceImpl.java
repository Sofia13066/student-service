package telran.java2022.studentservice;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.dto.ScoreDto;
import telran.java2022.dto.StudentCreateDto;
import telran.java2022.dto.StudentDto;
import telran.java2022.dto.StudentUpdateDto;
import telran.java2022.model.Student;
import telran.java2022.dao.StudentRepository;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	final StudentRepository studentRepository;

	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		if(studentRepository.findById(studentCreateDto.getId()).isPresent()) {
			return false;
		}
		Student student = new Student(studentCreateDto.getId(), 
				studentCreateDto.getName(), studentCreateDto.getPassword());
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		return student == null ? null : StudentDto.builder()
											.id(student.getId())
											.name(student.getName())
											.scores(student.getScores())
											.build();
	}

	@Override
	public StudentDto removeStudent(Integer id) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return null;
		}		
		studentRepository.deleteById(id);
		return StudentDto.builder()
							.id(id)
							.name(student.getName())
							.scores(student.getScores())
							.build();
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
		return StudentCreateDto.builder()
							.id(id)
							.name(student.getName())
							.password(student.getPassword())
							.build();
	}

	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElse(null);
		if(student == null) {
			return false;
		}
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
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(s -> s.getScores().containsKey(exam) && s.getScores().get(exam) > score)
				.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
				.collect(Collectors.toList());
	}

    @Override
    public List<StudentDto> findStudentByName(String name) {
        return studentRepository.findByNameIgnoreCase(name)
				.map(s -> new StudentDto(s.getId(), s.getName(), s.getScores()))
				.collect(Collectors.toList());
    }

}

