package lpm.Linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import lpm.Util.Constants;
import lpm.Util.Log;

public class Shell {
    public static final int exec(final String dir, final String... cmd) {
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

    public static final String SHA256(final String file) {
        try {
            ProcessBuilder pb = new ProcessBuilder("sha256sum", file);
            Process p = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            reader.close();
            
            p.waitFor();

            if (p.exitValue() != 0) {
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.err.println(errorLine);
                }
                errorReader.close();
                return null;
            }

            if (line != null && !line.isEmpty()) {
                return line.split(" ")[0];
            }

            return null;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final Install install = new Install();
}