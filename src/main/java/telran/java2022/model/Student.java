package telran.java2022.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Student {
    @Id
    Integer id;
    @Setter
    String name;
    @Setter
    String password;
    Map<String, Integer> scores = new HashMap<>();
    
    public Student(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public boolean addScore(String exam, int score){
        return scores.put(exam, score) == null;
    }
    
}
