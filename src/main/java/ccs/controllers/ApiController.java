package ccs.controllers;

import ccs.services.ShareLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ShareLogic.class);

    @GetMapping("/")
    public String main() {
        log.info("process method main");
        return "redirect:swagger-ui.html";
    }
}
