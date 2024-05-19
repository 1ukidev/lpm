package lpm.Util;

import java.io.IOException;

import lpm.Abstract.AbstractShell;

public class Shell implements AbstractShell {
    public final int exec(String workingDir, String... cmd) {
        Log.info("Running '" + String.join(" ", cmd) + "'...");

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);

            if (workingDir != null && workingDir.toLowerCase().equals("default")) {
                workingDir = Constants.lpmFolder;
            }

            pb.inheritIO();
            Process p = pb.start();
            return p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
