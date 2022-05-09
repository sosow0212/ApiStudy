package self.study.service.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.study.entity.v1.Board;
import self.study.repository.v1.BoardRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board writeBoard(Board board) {
        boardRepository.save(board);
        return board;
    }

    @Transactional(readOnly = true)
    public Board findById(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board id를 찾을 수 없습니다.");
        });
        return board;
    }

    @Transactional
    public Board editBoard(int id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글찾기실패 id 없음");
        });

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        return board;
    }


    @Transactional
    public String deleteBoard(int id) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글이 없습니다..");
        });
        boardRepository.deleteById(id);
        return "ok";
    }
}
