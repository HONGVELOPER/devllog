package devlog.hong.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql auto-incremnet 사용
    @Column(name = "img_id")
    private int id;

    @Column(nullable = false)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity postEntity;
}
