package lpm;

import static java.lang.System.exit;

import lpm.Util.Constants;
import lpm.Util.Log;
import lpm.Util.Others;

public class App {
    public static final void main(String[] args) {
        Others.checkHealth();

        if (args.length < 1) {
            Log.error("No option provided.");
            printHelp();
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
                Package.install(args[1]);
                break;
            default:
                Log.error("Unknown option: " + args[0]);
                printHelp();
                exit(1);
            }
    }

    public static final void printHelp() {
        Log.info("Usage: lpm [OPTION]...");
        Log.info("Commands:");
        Log.info("  install <package> Install a package");
        Log.info("Options:");
        Log.info("  -h, --help    Display this help and exit");
        Log.info("  -v, --version Display version information and exit");
    }

    public static final void printVersion() {
        Log.info("LuKi's Package Manager (lpm) " + Constants.version);
    }
}
