package com.tram.springbootangularboard.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;

@Slf4j
public class WebpackWatchExecutor {
    public WebpackWatchExecutor(String packageDir) {
        try {
            DefaultExecutor executor = new DefaultExecutor();
            executor.setWorkingDirectory(new File(packageDir));

            String line = getCommand();
            CommandLine cmdLine = CommandLine.parse(line);

            DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
            handler.waitFor(500);

            executor.execute(cmdLine, handler);
        } catch (Exception e) {
            log.error("WebpackWatcher", e);
            throw new RuntimeException(e);
        }
    }

    private String getCommand() {
        return System.getProperty("os.name").toLowerCase().contains("win") ? "npm.cmd run watch" : "npm run watch";
    }
}