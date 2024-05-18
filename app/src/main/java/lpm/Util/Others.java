package lpm.Util;

import static java.lang.System.exit;

public class Others {
    public static boolean isDebug = false;

    public static final void checkHealth() {
        if (!isDebug) {
            if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
                Log.error("This program only works on Linux.");
                Log.info("Current OS: " + System.getProperty("os.name").strip());
                exit(1);
            }
        }
    }
}