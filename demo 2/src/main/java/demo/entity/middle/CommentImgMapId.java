package demo.entity.middle;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommentImgMapId implements Serializable {

    private Long commentNo;
    private Long imageNo;
}
