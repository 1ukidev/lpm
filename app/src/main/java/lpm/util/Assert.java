package lpm.util;

import static java.lang.System.exit;

public class Assert {
    public static final void notNull(final Object obj, final String msg) {
        if (obj == null) {
            Log.error(msg);
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
