package lpm;

import lpm.Util.Assert;
import lpm.Util.Log;

public class Package {
    public static final void install(String name) {
        Assert.notNull(name, "Package name cannot be null.");
        Log.info("Installing " + name + "...");
        Log.success(name + " installed successfully.");
    }
}
