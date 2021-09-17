package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {

    private @Autowired BoardRepository boardRepository;

    public List<Board> getBoardList() {
        return boardRepository.findByisOpenIsTrue();
    }

    /**
     * @param boardNameQuery 查詢的 string
     * @return 模糊查詢的 Board List
     */

    public List<Board> getBoardListByBoardName(String boardNameQuery) {
        return boardRepository.findByBoardNameContains(boardNameQuery);
    }

    @Transactional
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    /**
     * Update board's attribute.
     *
     * @param queryName query board
     * @param newName   set board's new name
     * @param isOpen    set board's open state
     * @return Boolean is Success
     */

    @Transactional
    public void updateBoard(String queryName, String newName, boolean isOpen) {
        boardRepository.findByBoardName(queryName).ifPresentOrElse(
                board -> {
                    board.setBoardName(newName);
                    board.setOpen(isOpen);
                }, () -> {
                    throw new EntityNotFoundException("board not found, boardName : " + queryName);
                });
    }

}
