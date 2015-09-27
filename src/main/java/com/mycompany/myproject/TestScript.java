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

        oCmdLine.addArgument("sanga.txt");

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

    public void runfromCmd ()  {
        String [] commandline = new String[3];
        commandline[0] = "bash";
        commandline[1] = "-c";
        commandline[2] = "sh /Users/212433329 tc-script.sh";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(commandline);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception t) {
            t.printStackTrace();
        }


    }

    public void runCmdPrBld() {
        String[] cmd = { "bash", "-c", " /Users/212433329/tc-script.sh" };
        try {
            Process p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        TestScript testScript = new TestScript();
        //testScript.runCmdPrBld();
        testScript.runScript("sh tc-script.sh");
       // testScript.runfromCmd();
    }
}
