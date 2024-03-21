package org.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping (path = "/math")
public class DemoController {

    @GetMapping(path = "/sum")
    public @ResponseBody int getSum (){
        return 2;
    }

}
