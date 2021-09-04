package com.weitheshinobi.forum.controller.router;

import com.weitheshinobi.forum.service.BoardService;
import com.weitheshinobi.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
