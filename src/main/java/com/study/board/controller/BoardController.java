package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro") //BoardService의 write에서 throws Exception해줬기 때문에 동일하게 throws Exception해줘야함
    public String boardWritepro(Board board, Model model, MultipartFile file) throws Exception{
        boardService.write(board, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list") // @PageableDefault class의 ()안에서 ctrl+space를 눌러서 page = 0, size = 10사용하기
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10,  sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {
        //searchKeyword가 들어온 경우와 안들어온 경우를 나누어 준다.

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재 페이지를 가지고 온다
        int startPage = Math.max(nowPage - 4, 1);//매개변수로 들어온 두 값을 비교해서 더 높은 값을 반환(마이너스 페이지는 없기 때문에 Math클래스의 max메서드사용)
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";

        //sort는 어떤걸 기준으로 정렬할 건지, direction 정렬을 어떻게 해줄 것인지(DESC= 역순)
    }

    @GetMapping("/board/view")//localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}") //BoardService의 write에서 throws Exception해줬기 때문에 동일하게 throws Exception해줘야함
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception {
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);
        return "redirect:/board/list";

    }


}
