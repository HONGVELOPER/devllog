package devlog.hong.dto;

import devlog.hong.domain.entity.PostEntity;
import lombok.*;
import org.jsoup.Jsoup;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PostResponseDto {

    private int id;
    private String title;
    private String content;
    private String author;
    private int viewCount;
    private String thumbNail;
    private String date;
    private List<ImageResponseDto> imageResponseDtoList;

    public void setImageResponseDtoList(List<ImageResponseDto> imageResponseDtoList) {
        this.imageResponseDtoList = imageResponseDtoList;
    }

    public PostResponseDto(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.viewCount = post.getViewCount();
        this.thumbNail = post.getThumbNail();
        this.date = post.getUpdatedDate().split("\\s")[0];
    }

    public PostResponseDto regex() {
        if (this.content.length() <= 250) {
            this.content = Jsoup.parse(this.content).text();
        } else {
            this.content = Jsoup.parse(this.content).text().substring(0, 250) + " ···";
        }
        return this;
    }
}
