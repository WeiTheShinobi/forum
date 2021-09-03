package com.weitheshinobi.forum.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> listBoard() {
        return boardRepository.findAll();
    }

    public List<Board> listBoardByBoardName(String boardNameQuery) {
        return boardRepository.findByBoradNameLike("%" + boardNameQuery + "%");
    }

    @Transactional
    public Board createBoard(String boardName) {
        Board board = new Board(boardName, true);
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long id, String boardName, boolean isOpen) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        board.setBoradName(boardName);
        board.setOpen(isOpen);
        return boardRepository.save(board);
    }

}
