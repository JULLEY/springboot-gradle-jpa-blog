package leo.programming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrawlingDto {

    // 제목
    private String title;
    // 링크
    private String link;
    // 이미지 url
    private String imageUrl;
    // 내용
    private String content;

}
