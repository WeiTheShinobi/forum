package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    /**
     * @param boardNameQuery 查詢的 string
     * @return 模糊查詢的 Board List
     */

    public List<Board> getBoardListByBoardName(String boardNameQuery) {
        return boardRepository.findByBoardNameLike("%" + boardNameQuery + "%");
    }

    @Transactional
    public Board createBoard(String boardName) {
        return boardRepository.save(new Board(boardName, true));
    }

    /**
     * @param queryName query board
     * @param newName set board's new name
     * @param isOpen set board's open state
     * @return is Success
     */

    @Transactional
    public boolean updateBoard(String queryName, String newName, boolean isOpen) {
        Optional<Board> boardOptional = boardRepository.findByBoardName(queryName);
        boardOptional.ifPresent(
                board -> {
                    board.setBoardName(newName);
                    board.setOpen(isOpen);
                });
        return boardOptional.isPresent();
    }

}
