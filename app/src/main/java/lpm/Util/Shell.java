package lpm.Util;

import java.io.IOException;

public class Shell {
    public static final int exec(String... cmd) {
        Log.info("Running '" + String.join(" ", cmd) + "'...");

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.inheritIO();
            Process p = pb.start();
            return p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
