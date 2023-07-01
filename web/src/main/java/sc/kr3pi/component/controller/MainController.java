/*
 * Copyright (c) 2022.   KR3PI
 */

package sc.kr3pi.component.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



    @GetMapping("/DailyForm")
    public String techForm(){ return "view/form/Daily";}
}
