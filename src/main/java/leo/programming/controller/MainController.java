package leo.programming.controller;

import leo.programming.dto.CrawlingDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    /**
     * 메인 접근 && IT 기사 크롤링
     * @param model
     * @param pageable
     * @return
     * @throws IOException
     */
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) throws IOException {

        List<CrawlingDto> crawlingList = new ArrayList<>();
        Document doc = Jsoup.connect("https://androidweekly.net/").get();
        Elements infoList = doc.select("div[class=sections]").select("a[class=article-headline]");
        Elements contentList = doc.select("span.main-url + p");
        for (int i = 0; i < infoList.size(); i++) {

            CrawlingDto crawling = CrawlingDto.builder()
                    .title(infoList.get(i).text())
                    .link(infoList.get(i).attr("href"))
                    .imageUrl(doc.select("a[href=" + infoList.get(i).attr("href") + "]").select("img").attr("src"))
                    .content(contentList.get(0).text())
                    .build();

            crawlingList.add(crawling);
        }
        model.addAttribute("crawlingList", crawlingList);

        return "index";
    }
}
