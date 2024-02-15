package ever.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "EXPHISTORY")
/**
 * TODO : reason String -> Enum
 */
public class ExpHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member; // 단방향으로 잡아놈

    private String reason;
    private int amount;

}
