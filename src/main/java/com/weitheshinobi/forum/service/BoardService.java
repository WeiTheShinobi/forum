package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
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

    public Board createBoard(String boardName) {
        Board board = new Board(boardName, true);
        return boardRepository.save(board);
    }

    public Optional<Board> updateBoard(String queryName, String boardName, boolean isOpen) {
        Optional<Board> byBoardName = boardRepository.findByBoardName(queryName);
        if (byBoardName.isPresent()) {
            Board board = byBoardName.get();
            board.setBoardName(boardName);
            board.setOpen(isOpen);
            return Optional.of(board);
        }
        return byBoardName;
    }

}
