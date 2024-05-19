package lpm.Util;

import static java.lang.System.exit;

public class Assert {
    public static final void notNull(Object obj, String s) {
        if (obj == null) {
            Log.error(s);
            exit(1);
        }
    }

    public static final void notEmpty(String s, String msg) {
        if (s.isEmpty()) {
            Log.error(msg);
            exit(1);
        }
    }
}
