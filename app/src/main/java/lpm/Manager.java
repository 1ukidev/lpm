package lpm;

import static java.lang.System.exit;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lpm.Abstract.AbstractManager;
import lpm.Entity.PackageEntity;
import lpm.Util.Assert;
import lpm.Util.Constants;
import lpm.Util.Log;
import lpm.Util.Shell;
import lpm.Util.Web;

public class Manager implements AbstractManager {
    public final void install(String name) {
        Assert.notNull(name, "Package name cannot be null.");

        Log.info("Installing " + name + "...");

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Set<PackageEntity> pkgs = objectMapper.readValue(new File(Constants.lpmPackagesFile), new TypeReference<Set<PackageEntity>>() {});
            Map<String, PackageEntity> pkgsMap = pkgs.stream().collect(Collectors.toMap(PackageEntity::getName, packageEntity -> packageEntity));

            PackageEntity pkg = pkgsMap.get(name);

            if (pkg != null) {
                Assert.notEmpty(pkg.getUrl(), "Package URL cannot be empty.");
                Assert.notEmpty(pkg.getName(), "Package name cannot be empty.");

                Shell shell = new Shell();
                Web web = new Web();

                Log.info("Downloading " + name + "...");
                int downloadPkg = web.get(pkg.getUrl(), Constants.lpmFolder + "/" + name + ".tar.gz");
                if (downloadPkg != 0) {
                    throwInstallError();
                }

                if (pkg.getUrl().endsWith(".tar.gz")) {
                    int runMkdir = shell.exec("default", "mkdir", "-p", Constants.lpmFolder + "/" + name);
                    if (runMkdir != 0) {
                        throwInstallError();
                    }

                    int runTar = shell.exec("default", "tar", "xzf", Constants.lpmFolder + "/" + name + ".tar.gz", "-C", Constants.lpmFolder + "/" + name);
                    if (runTar != 0) {
                        throwInstallError();
                    }

                    int runRm = shell.exec("default", "rm", Constants.lpmFolder + "/" + name + ".tar.gz");
                    if (runRm != 0) {
                        throwInstallError();
                    }

                    int runSteps = shell.exec("default", "sh", "-c", "cd " + Constants.lpmFolder + "/" + name + " && " + pkg.getSteps());
                    if (runSteps != 0) {
                        throwInstallError();
                    }
                } else {
                    Log.error("Only .tar.gz packages are supported.");
                    exit(1);
                }
            } else {
                Log.error("Package not found.");
                exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }

        Log.success(name + " installed successfully in " + Constants.lpmFolder + "/" + name);
    }

    public final void remove(String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public final void run(String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public final void refresh() {
        Log.error("Not implemented yet.");
        exit(1);
    }

    public final void throwInstallError() {
        Log.error("Failed to install package.");
        exit(1);
    }
}
