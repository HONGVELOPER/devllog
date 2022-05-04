package devlog.hong.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-incremnet 사용
    @Column(name = "img_id")
    private int id;

    @Column(nullable = false)
    private String img;

    @ManyToOne
    @JoinColumn(name = "post_id") // N:1 양방향 , 관계 주인 Post
    private PostEntity postEntity;

    @Builder
    public ImageEntity(String img) {
        this.img = img;
    }
}
