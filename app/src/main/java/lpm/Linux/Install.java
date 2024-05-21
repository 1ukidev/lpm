package lpm.Linux;

import lpm.Util.Constants;

public class Install {
    public final int execTar(final String name) {
        return Shell.exec(Constants.lpmFolder, "tar", "xzf", name + ".tar.gz", "-C", name);
    }

    public final int execSteps(final String pkgName, final String steps) {
        return Shell.exec(Constants.lpmFolder + "/" + pkgName, "sh", "-c", steps);
    }
}
