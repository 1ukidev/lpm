package lpm;

import static java.lang.System.exit;

import java.io.File;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lpm.Entity.PackageEntity;
import lpm.Linux.Shell;
import lpm.Util.Assert;
import lpm.Util.Constants;
import lpm.Util.Log;
import lpm.Util.Web;

public class Manager {
    public static final void install(String name) {
        Assert.notNull(name, "Package name cannot be null.");

        Log.info("Installing " + name + "...");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Set<PackageEntity> pkgs = objectMapper.readValue(new File(Constants.lpmPackagesFile),
                                                             new TypeReference<Set<PackageEntity>>() {});

            PackageEntity pkg = null;
            for (PackageEntity packageEntity : pkgs) {
                if (packageEntity.getName().equals(name)) {
                    pkg = packageEntity;
                    break;
                }
            }

            if (pkg == null) {
                Log.error("Package not found.");
                exit(1);
            }

            Assert.notEmpty(pkg.getUrl(), "Package URL cannot be empty.");
            Assert.notEmpty(pkg.getName(), "Package name cannot be empty.");

            if (!pkg.getUrl().endsWith(".tar.gz")) {
                Log.error("Only .tar.gz packages are supported.");
                exit(1);
            }

            Log.info("Downloading " + name + "...");
            int downloadPkg = Web.get(pkg.getUrl(), Constants.lpmFolder + "/" + name + ".tar.gz");
            if (downloadPkg != 0) {
                throwInstallError();
            }

            int execMkdir = Shell.install.execMkdir(name);
            if (execMkdir != 0) {
                throwInstallError();
            }

            int execTar = Shell.install.execTar(name);
            if (execTar != 0) {
                throwInstallError();
            }

            int execRm = Shell.install.execRm(name);
            if (execRm != 0) {
                throwInstallError();
            }

            int execSteps = Shell.install.execSteps(name, pkg.getSteps());
            if (execSteps != 0) {
                throwInstallError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }

        Log.success(name + " installed successfully in " + Constants.lpmFolder + "/" + name);
    }

    public static final void remove(String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void run(String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void refresh() {
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void list() {
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void throwInstallError() {
        Log.error("Failed to install package.");
        exit(1);
    }
}
