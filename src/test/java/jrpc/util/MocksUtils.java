package jrpc.util;

import jrpc.clightning.model.CLightningGetInfo;
import jrpc.clightning.service.CLightningConfigurator;
import jrpc.service.CLightningLogger;
import jrpc.service.converters.JsonConverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class MocksUtils {

    public static CLightningGetInfo getInfoFirstNode() {
        String url = CLightningConfigurator.getInstance().getUrl();
        StringTokenizer tokens = new StringTokenizer(url, "/");
        if (tokens.countTokens() > 1) {
            StringBuilder builder = new StringBuilder("/");
            while (tokens.hasMoreElements()) {
                String token = tokens.nextToken();
                if (token.contains("lightning_dir")) {
                    break;
                }
                builder.append(token).append("/");
            }
            builder.append("node_one.info");
            byte[] encoded = new byte[0];
            try {
                encoded = Files.readAllBytes(Paths.get(builder.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String getInfo = new String(encoded);
            JsonConverter converter = new JsonConverter();
            return (CLightningGetInfo) converter.deserialization(getInfo, CLightningGetInfo.class);
        }
        return null;
    }


    // deprecated stuff
    public static void runBitcoin() {
        runProcess("run-bitcoin.sh");
    }

    public static void stopBitcoin() {
        runProcess("stop-bitcoin.sh");
    }

    public static void generateBlockBitcoin() {
        runProcess("generate-block-bitcoin.sh");
    }

    public static void startCLightningNodeOne() {
        runProcess("run_node_one.sh");
    }

    public static void startCLightningNodeTwo() {
        runProcess("run_node_two.sh");
    }

    public static void runCLightningNodes() {
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
            CLightningLogger.getInstance().error(MocksUtils.class, pb.redirectOutput().toString());
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }
}
