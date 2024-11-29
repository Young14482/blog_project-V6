package com.example.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@Import(BoardRepository.class)
@DataJpaTest // DB 관련된 자원들을 메모리에 올린다.
public class BoardRepositoryTest { // 클래스 명 뒤에 Test를 붙이는게 약속이다.
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void findById_test() {
        // given
        Integer id = 1;

        // when
        Optional<Board> boardOP = boardRepository.findById(id);
        Board board = boardOP.get();

        // eye
        System.out.println("레이지 직전");
        String title = board.getUser().getUsername();
        System.out.println("레이지 직후");
    }

    @Test
    public void update_test() {
        // given
        int id = 1;
        String title = "제목1수정";
        String content = "내용1수정";

        // when
//        boardRepository.update(id, title, content);

        // then(eye)
//        Board board = boardRepository.findById(id);
//        System.out.println(board.getTitle());
//        System.out.println(board.getContent());
    }

    @Test
    public void delete_test() {
        // given
        int id = 1;

        // when
        boardRepository.delete(id);

        // then(eye)
        List<Board> boardList = boardRepository.findAll();
        System.out.println("size: " + boardList.size());
    }

    @Test
    public void save_test() {
        // given
        String title = "제목6";
        String content = "내용6";
        // when
        boardRepository.save(Board.builder().title(title).content(content).build());
        // then(eye)
        Optional<Board> board = boardRepository.findById(6);
        System.out.println(board.get().getId());
        System.out.println(board.get().getTitle());
        System.out.println(board.get().getContent());
        System.out.println(board.get().getCreatedAt());
    } // rollback (@Transactional)

    // 메서드명 뒤에 _test를 붙이는게 약속이다.
    @Test
    public void findAll_test() {
        // given parameter 입력할 매개변수
        // findAll에 매개변수가 없어서 생략

        // when method 테스트할 메서드
        List<Board> boardList = boardRepository.findAll();
        // then verify 검증

        // eye 직접 확인
        for (Board board : boardList) {
            System.out.println(board.getId());
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
            System.out.println(board.getCreatedAt());
            System.out.println("============");
        }
    }
}