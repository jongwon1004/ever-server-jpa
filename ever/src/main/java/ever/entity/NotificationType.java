package ever.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "NOTIFICATIONTYPE")
public class NotificationType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationTypeNo")
    private Long id;

    private String notificationTypeName;


}
