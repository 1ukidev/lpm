package lpm.Linux;

import lpm.Util.Constants;

public class Install {
    public final int extractTar(final String file, final String dest) {
        return Shell.exec(Constants.lpmFolder, "tar", "xzf", file, "-C", dest);
    }

    public final int execSteps(final String folder, final String steps) {
        return Shell.exec(folder, "sh", "-c", steps);
    }
}
