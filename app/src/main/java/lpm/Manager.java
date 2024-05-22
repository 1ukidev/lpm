package lpm;

import static java.lang.System.exit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import lpm.Entity.PackageEntity;
import lpm.Linux.Shell;
import lpm.Util.Assert;
import lpm.Util.Constants;
import lpm.Util.Log;
import lpm.Util.Web;

public class Manager {
    public static final void install(final String name) {
        Assert.notNull(name, "Package name cannot be null.");

        Log.info("Installing " + name + "...");

        String pkgFolder = null;

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(Constants.lpmPackagesFile)));
            Set<PackageEntity> pkgs = JSON.parseObject(jsonContent, new TypeReference<Set<PackageEntity>>() {});

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

            pkgFolder = Constants.lpmFolder + "/" + name;

            Log.info("Downloading " + name + "...");
            String tarGz = pkgFolder + ".tar.gz";
            int downloadPkg = Web.get(pkg.getUrl(), tarGz);
            if (downloadPkg != 0) {
                throwInstallError();
            }

            Log.info("Verifying checksum...");
            String SHA256 = Shell.SHA256(tarGz);
            boolean check = SHA256.equals(pkg.getSHA256());
            if (!check) {
                Log.error("Checksum mismatch.");
                exit(1);
            }

            new File(pkgFolder).mkdirs();

            int extractTar = Shell.install.extractTar(tarGz, pkgFolder);
            if (extractTar != 0) {
                throwInstallError();
            }

            new File(tarGz).delete();

            int execSteps = Shell.install.execSteps(pkgFolder, pkg.getSteps());
            if (execSteps != 0) {
                throwInstallError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            exit(1);
        }

        if (pkgFolder != null) {
            Log.success(name + " installed successfully in " + pkgFolder);
        }
    }

    public static final void remove(final String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void run(final String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.error("Not implemented yet.");
        exit(1);
    }

    public static final void refresh() {
        Log.info("Refreshing packages...");
        int download = Web.get(Constants.lpmPackagesUrl, Constants.lpmPackagesFile);
        if (download != 0) {
            Log.error("Failed to refresh packages.");
            exit(1);
        }
    }

    public static final void list() {
        Log.error("Not implemented yet.");
        exit(1);
    }

    private static final void throwInstallError() {
        Log.error("Failed to install package.");
        exit(1);
    }
}
