package com.mycompany.myproject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class TestScript {
    int iExitValue;
    String sCommandString;

    public void runScript(String command){
        sCommandString = command;
        CommandLine oCmdLine = CommandLine.parse(sCommandString);
        DefaultExecutor oDefaultExecutor = new DefaultExecutor();
       // oDefaultExecutor.setWorkingDirectory(new File(command.getWorkingDir()).getAbsoluteFile());
       // oDefaultExecutor.setExitValue(1);
        try {
        	System.out.println(oDefaultExecutor.getWorkingDirectory().getAbsolutePath());
        	oDefaultExecutor.setWorkingDirectory(oDefaultExecutor.getWorkingDirectory());
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

    public static void main(String args[]){
        TestScript testScript = new TestScript();
        testScript.runScript("sh sample-script.sh");
    }
}
