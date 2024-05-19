package lpm.Util;

import static java.lang.System.exit;

import java.io.File;

public class Others {
    public static final void checkHealth() {
        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            Log.error("This program only works on Linux.");
            Log.info("Current OS: " + System.getProperty("os.name").strip());
            exit(1);
        }

        File lpmFolder = new File(Constants.lpmFolder);
        if (!lpmFolder.exists()) {
            Log.info("Creating ~/.lpm folder...");
            if (!lpmFolder.mkdirs()) {
                Log.error("Failed to create ~/.lpm folder.");
                exit(1);
            }
        }
    }
}
