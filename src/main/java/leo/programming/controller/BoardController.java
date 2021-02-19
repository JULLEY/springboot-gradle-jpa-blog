package leo.programming.controller;

import leo.programming.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /**
     * 커뮤니티메인
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards", boardService.selectBoardList(pageable));

        return "board/board";
    }

    /**
     * 게시글 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable int id, Model model){
        model.addAttribute("board" , boardService.selectBoardDetail(id));
        return "board/detail";
    }

    // USER 권한이 필요

    /**
     * 게시글 등록폼
     * @return
     */
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    /**
     * 게시글 수정폼
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.selectBoardDetail(id));
        return "board/updateForm";
    }
}
