package lpm;

import static java.lang.System.exit;

import lpm.Util.Constants;
import lpm.Util.Log;
import lpm.Util.Others;

public class App {
    public static void main(String[] args) {
        Others others = new Others();
        others.checkSystem();

        if (args.length < 1) {
            Log.error("No option provided.");
            Log.error("Try 'lpm --help' for more information.");
            exit(1);
        }

        Manager manager = new Manager();

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
                manager.install(args[1]);
                exit(0);

            case "remove":
                if (args.length < 2) {
                    Log.error("No package provided.");
                    exit(1);
                }
                manager.remove(args[1]);
                exit(0);

            case "run":
                if (args.length < 2) {
                    Log.error("No package provided.");
                    exit(1);
                }
                manager.run(args[1]);
                exit(0);

            case "refresh":
                manager.refresh();
                exit(0);

            default:
                Log.error("Unknown option: " + args[0]);
                Log.error("Try 'lpm --help' for more information.");
                exit(1);
        }
    }

    public static final void printHelp() {
        Log.info("Usage: lpm [OPTION]...");
        Log.info("Commands:");
        Log.info("  install <package> Install a package");
        Log.info("  remove  <package> Remove a package");
        Log.info("  run     <package> Run a package");
        Log.info("  refresh           Refresh the package list");
        Log.info("Options:");
        Log.info("  -h, --help    Display this help and exit");
        Log.info("  -v, --version Display version information and exit");
    }

    public static final void printVersion() {
        Log.info("LuKi's Package Manager (lpm) (" + Constants.version + ")");
    }
}
