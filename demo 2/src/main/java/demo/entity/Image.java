package demo.entity;

import demo.enums.EntityType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "IMAGE")
public class Image extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EntityType entityType;

    @OneToMany(mappedBy = "image")
    private List<BoardImgMap> boardImgMaps = new ArrayList<>();

    private Integer entityId;
    private String serverFilename;
    private String clientFilename;

}
