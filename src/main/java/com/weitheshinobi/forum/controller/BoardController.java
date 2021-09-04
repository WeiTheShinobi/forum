package com.weitheshinobi.forum.controller;

import com.weitheshinobi.forum.service.BoardService;
import com.weitheshinobi.forum.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/listBoard", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Board> listBoard() {
        return boardService.getBoardList();
    }

}
