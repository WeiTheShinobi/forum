package com.weitheshinobi.forum.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouterController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/{boardName}")
    public String board(@PathVariable String boardName, Model model) {
        model.addAttribute("boardName", boardName);
        return "board";
    }
}
