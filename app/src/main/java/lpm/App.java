package lpm;

import static java.lang.System.exit;

import lpm.util.Constants;
import lpm.util.Log;
import lpm.util.Others;

public class App {
    public static final void main(String[] args) {
        Others.checkSystem();

        if (args.length < 1) {
            Log.error("No option provided.");
            Log.error("Try 'lpm --help' for more information.");
            exit(1);
        }

        switch (args[0]) {
            case "-h":
            case "--help":
                printHelp();
                exit(0);
            case "-v":
            case "--version":
                printVersion();
                exit(0);
            case "install":
                if (args.length < 2) {
                    Log.error("No package provided.");
                    exit(1);
                }
                Manager.install(args[1]);
                exit(0);
            case "remove":
                if (args.length < 2) {
                    Log.error("No package provided.");
                    exit(1);
                }
                Manager.remove(args[1]);
                exit(0);
            case "run":
                if (args.length < 2) {
                    Log.error("No package provided.");
                    exit(1);
                }
                Manager.run(args[1]);
                exit(0);
            case "refresh":
                Manager.refresh();
                exit(0);
            case "list":
                Manager.list();
                exit(0);
            default:
                Log.error("Unknown option: " + args[0]);
                Log.error("Try 'lpm --help' for more information.");
                exit(1);
        }
    }

    private static final void printHelp() {
        Log.info("Usage: lpm [OPTION]...");
        Log.info("Commands:");
        Log.info("  install <package> Install a package");
        Log.info("  remove  <package> Remove a package");
        Log.info("  run     <package> Run a package");
        Log.info("  refresh           Refresh the package list");
        Log.info("  list              List all installed packages");
        Log.info("Options:");
        Log.info("  -h, --help    Display this help and exit");
        Log.info("  -v, --version Display version information and exit");
    }

    private static final void printVersion() {
        Log.info(Constants.version);
    }
}
