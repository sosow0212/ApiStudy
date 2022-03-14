package self.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import self.study.entity.Board;
import self.study.repository.BoardRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findAll() {
        return boardRepository.findAll();
    }


    public Board writeBoard(Board board) {
        boardRepository.save(board);
        return board;
    }

    public Board findById(int id) {
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("Board id를 찾을 수 없습니다.");
        });
        return board;
    }

}
