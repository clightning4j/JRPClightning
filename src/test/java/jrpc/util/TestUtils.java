package jrpc.util;

import jrpc.service.CLightningLogger;

import java.io.File;
import java.io.IOException;

public class TestUtils {

    public static void runBitcoin(){
        runProcess("run-bitcoin.sh");
    }

    public static void stopBitcoin(){
        runProcess("stop-bitcoin.sh");
    }

    public static void generateBlockBitcoin(){
        runProcess("generate-block-bitcoin.sh");
    }

    public static void startCLightningNodeOne(){
        runProcess("run_node_one.sh");
    }

    public static void startCLightningNodeTwo(){
        runProcess("run_node_two.sh");
    }

    public static void runCLightningNodes(){
        startCLightningNodeTwo();
        startCLightningNodeOne();
    }

    private static void runProcess(String cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder("bash", new File(cmd).toString());
            pb.inheritIO();
            pb.directory(new File("/media/vincent/Maxtor/sanboxTestWrapperRPC/"));
            Process process = pb.start();
            process.waitFor();
            CLightningLogger.getInstance().error(TestUtils.class, pb.redirectOutput().toString());
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }
}
