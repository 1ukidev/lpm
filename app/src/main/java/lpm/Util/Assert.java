package lpm.Util;

import static java.lang.System.exit;

public class Assert {
    public static final void notNull(Object obj, String s) {
        if (obj == null) {
            Log.error(s);
            exit(1);
        }
    }
}
