package ever.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "LANGUAGETYPE")
public class LanguageType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageTypeNo")
    private Long id;

    private String languageTypeName;

    @OneToMany(mappedBy = "languageType")
    private List<Language> languages = new ArrayList<>();

    public LanguageType(String languageTypeName) {
        this.languageTypeName = languageTypeName;
    }
}
