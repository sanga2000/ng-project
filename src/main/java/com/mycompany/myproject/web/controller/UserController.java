package com.mycompany.myproject.web.controller;

import com.mycompany.myproject.service.UserService;
import com.mycompany.myproject.service.dto.UserDto;
import org.apache.commons.exec.*;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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

    int iExitValue;
    String sCommandString;
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<UserDto> usersList() {
        logger.debug("get json user list");
        return userService.findAll();
    }

    @RequestMapping(value = "/execTest", method = RequestMethod.GET)
    public @ResponseBody void executeTestCase(@RequestParam("tcId") String tcId, @RequestParam ("testBed") String testBed) {

        tcId = StringUtils.replace(tcId, ",", " ");
        runScript("sh tc-script.sh");

        //return userService.findAll();
    }

    public void runScript(String command){
        sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
        // oDefaultExecutor.setWorkingDirectory(new File(command.getWorkingDir()).getAbsoluteFile());
        // oDefaultExecutor.setExitValue(1);
        try {

            oDefaultExecutor.setWorkingDirectory(new File("/Users/212433329/bharshell"));
            iExitValue = oDefaultExecutor.execute(oCmdLine);
        } catch (ExecuteException e) {
            // TODO Auto-generated catch block
            System.err.println("Execution failed.");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("permission denied.");
            e.printStackTrace();
        }
    }

}


