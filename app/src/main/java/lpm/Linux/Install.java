package lpm.Linux;

import lpm.Util.Constants;

public class Install {
    public final int execMkdir(String name) {
        return Shell.exec(Constants.lpmFolder, "mkdir", "-p", name);
    }

    public final int execTar(String name) {
        return Shell.exec(Constants.lpmFolder, "tar", "xzf", name + ".tar.gz", "-C", name);
    }

    public final int execRm(String name) {
        return Shell.exec(Constants.lpmFolder, "rm", name + ".tar.gz");
    }

    public final int execSteps(String pkgName, String steps) {
        return Shell.exec(Constants.lpmFolder + "/" + pkgName, "sh", "-c", steps);
    }
}
