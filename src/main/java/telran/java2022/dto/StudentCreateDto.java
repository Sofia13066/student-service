package telran.java2022.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentCreateDto {
    Integer id;
    String name;
    String password;
    
}
