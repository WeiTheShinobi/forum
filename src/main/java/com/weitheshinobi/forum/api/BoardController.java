package com.weitheshinobi.forum.api;

import com.weitheshinobi.forum.service.BoardService;
import com.weitheshinobi.forum.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/boardlist")
    public ResponseEntity<List<Board>> listBoard() {
        return ResponseEntity.ok().body(boardService.getBoardList());
    }

}
