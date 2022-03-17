package devlog.hong.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-increment 사용
    @Column(name = "post_id")
    private int id;

    @Column(nullable = false) // @Notnull 과 차이 비교해보자.
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private String thumbNail;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private List<ImageEntity> images = new ArrayList<>();

//    public void addImages(ImageEntity image) {
//        images.add(image);
//    }

}
