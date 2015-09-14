package com.mycompany.myproject.web.controller;

import com.mycompany.myproject.service.UserService;
import com.mycompany.myproject.service.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("request")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource ms;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<UserDto> usersList() {
        logger.debug("get json user list");
        return userService.findAll();
    }

    @RequestMapping(value = "/execTest", method = RequestMethod.GET)
    public @ResponseBody void executeTestCase(@RequestParam("tcId") String tcId, @RequestParam ("testBed") String testBed) {
        logger.debug("INSIDE EXECUTE CASES******************" +tcId );
        System.out.println(("INSIDE EXECUTE CASES******************" +tcId ));
        tcId = StringUtils.replace(tcId, ",", " ");
        ProcessBuilder pb = new ProcessBuilder("tc-script.sh", tcId, testBed);
        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return userService.findAll();
    }


}


