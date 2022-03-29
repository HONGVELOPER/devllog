package devlog.hong.domain.entity;

import devlog.hong.domain.dto.PostReqDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "posts")
@Builder
@ToString
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


//    public PostEntity(PostReqDto postReqDto) {
//        this.title = postReqDto.getTitle();
//        this.content = postReqDto.getContent();
//        this.writer = postReqDto.getWriter();
//        this.viewCount = postReqDto.getViewCount();
//        this.thumbNail = postReqDto.getThumbNail();
//    }

}

