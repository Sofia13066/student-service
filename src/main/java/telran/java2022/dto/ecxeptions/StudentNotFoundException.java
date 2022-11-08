package telran.java2022.dto.ecxeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -2898404505161550864L;

    public StudentNotFoundException(int id){
        super("Student with id " + id + " is not found");
    }
}
