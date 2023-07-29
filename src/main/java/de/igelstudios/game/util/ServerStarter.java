package de.igelstudios.game.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ServerStarter {
    //private static Process process;
    private ServerStarter(){

    }

    public static void create(){
        if(!new File("server.bat").exists())createFile();
        /*ProcessBuilder builder = new ProcessBuilder("server.bat");
        try {
            process = builder.start();
            Thread err = new Thread(() -> {
                try {

                    System.err.println(new String(process.getErrorStream().readAllBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread out = new Thread(() -> {
                try {
                    System.out.println(new String(process.getInputStream().readAllBytes()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            out.start();
            err.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    private static void createFile() {
        try {
            File file = new File("server.bat");
            if(!file.createNewFile())throw new IOException("Could not create Server file");
            Files.writeString(Path.of("server.bat"),"java -cp libs/* de.igelstudios.ServerMain");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void terminate(){
        if(process != null)process.destroy();
    }*/
}
