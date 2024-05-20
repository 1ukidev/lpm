package lpm.Linux;

import java.io.File;
import java.io.IOException;

import lpm.Util.Constants;
import lpm.Util.Log;

public class Shell {
    public static final int exec(String dir, String... cmd) {
        Log.info("Running '" + String.join(" ", cmd) + "'...");

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);

            if (dir != null) {
                pb.directory(new File(dir));
            } else {
                pb.directory(new File(Constants.lpmFolder));
            }

            pb.inheritIO();
            Process p = pb.start();
            return p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static final Install install = new Install();
}