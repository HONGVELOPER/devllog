package devlog.hong.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
@ToString
public class PostEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-increment 사용
    @Column(name = "post_id")
    private int id;

    @Column(nullable = false) // @Notnull 과 차이 비교해보자.
    private String title;

    @Column(nullable = false, length=10000)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private String thumbNail;

    @OneToMany(mappedBy = "postEntity", cascade = {CascadeType.ALL}, orphanRemoval=true) // N:1 양방향 , 관계 주인 Post
    List<ImageEntity> imageEntityList = new ArrayList<>();

    @Builder
    public PostEntity(String title, String content, String author, int viewCount, String thumbNail) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = viewCount;
        this.thumbNail = thumbNail;
    }

    public void update(String title, String content, String author, String thumbNail) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.thumbNail = thumbNail;
    }

    public void addImageEntity(ImageEntity imageEntity) {
        imageEntityList.add(imageEntity);
        imageEntity.setPostEntity(this);
    }
}


