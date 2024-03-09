package ever.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardListQueryDetails {

    private String languageTypeName;
    private String languageName;
    private Integer page;
    private String search;
    private Integer offset;
}
