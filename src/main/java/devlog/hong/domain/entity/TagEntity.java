package devlog.hong.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tags")
@ToString
public class TagEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int id;

    @Column(nullable = true)
    private String tag;

    @OneToMany(mappedBy = "tagEntity")
    List<PostEntity> postEntityList = new ArrayList<>();

    @Builder
    public TagEntity(String tag) {
        this.tag = tag;
    }
}
