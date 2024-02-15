package ever.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERCLASS")
public class UserClass {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classNo")
    private Long id;

    private String className;

    public UserClass(String className) {
        this.className = className;
    }
}
