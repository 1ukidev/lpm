package lpm.Util;

import static java.lang.System.exit;

import java.io.File;

public class Others {
    public static final void checkSystem() {
        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            Log.error("This program only works on Linux.");
            Log.info("Current OS: " + System.getProperty("os.name").strip());
            exit(1);
        }

        if (!new File(Constants.lpmFolder).exists()) {
            Log.info("Creating ~/.lpm folder...");

            try {
                if (!new File(Constants.lpmFolder).mkdirs()) {
                    Log.error("Failed to create ~/.lpm folder.");
                    exit(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                exit(1);
            }
        }

        if (!new File(Constants.lpmPackagesFile).exists()) {
            Log.info("Fetching ~/.lpm/packages.json file...");

            int status = Web.get(Constants.lpmPackagesUrl, Constants.lpmPackagesFile);

            if (status == 1) {
                Log.error("Failed to fetch ~/.lpm/packages.json file.");
                exit(1);
            }
        }
    }
}
