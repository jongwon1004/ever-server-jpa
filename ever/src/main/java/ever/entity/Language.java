package ever.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "LANGUAGE")
public class Language {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageNo")
    private Long id;

    private String languageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "languageTypeNo")
    private LanguageType languageType;

    public Language(String languageName, LanguageType languageType) {
        this.languageName = languageName;
        setLanguageType(languageType);
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
        languageType.getLanguages().add(this);
    }
}
