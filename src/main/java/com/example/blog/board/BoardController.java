package com.example.blog.board;

import com.example.blog.user.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board/save")
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return "redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
        boardService.게시글수정하기(id, updateDTO);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.게시글삭제(id, sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, Model model) {
        BoardResponse.UpdateFormDTO updateFormDTO = boardService.게시글수정화면보기(id);
        model.addAttribute("model", updateFormDTO);
        return "/board/update-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id, sessionUser);
        model.addAttribute("model", boardDetail);
        return "/board/detail";
    }

    @GetMapping("/")
    public String list(Model model) { // DS(request객체를 model이라는 객체로 랩핑해서 전달해준다)
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        model.addAttribute("models", boardList);
        return "index";
    }
}












