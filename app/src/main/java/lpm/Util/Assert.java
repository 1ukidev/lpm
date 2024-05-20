package lpm.Util;

import static java.lang.System.exit;

public class Assert {
    public static final void notNull(final Object obj, final String s) {
        if (obj == null) {
            Log.error(s);
            exit(1);
        }
    }

    public static final void notEmpty(final String s, final String msg) {
        if (s.isEmpty()) {
            Log.error(msg);
            exit(1);
        }
    }
}
