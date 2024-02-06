package demo.entity.middle;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BoardImgMap 의 복합 키를 나타내는데 사용하는 클래스
 * BoardImgMap 엔티티에서 이 복합 키 클래스를 사용하여 @EmbeddedId 어노테이션으로 복합 키를 정의함
 *
 * @EmbeddableId 를 적용한 식별자 클래스는 다음의 조건을 만족해야함.
 * 1. @Embeddable 적용
 * 2. Serializable 인터페이스 구현
 * equals, hashCode 구현 - 식별자로 사용하기 위함
 * 기본 생성자 필수
 * 클래스 접근 제어자 범위 public
 */
@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardImgMapId implements Serializable {

    private Integer globalBoardNo;
    private Integer imageNo;
}
