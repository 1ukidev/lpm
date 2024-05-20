package lpm.Util;

public class Log {
    public static final void info(final String s) {
        System.out.println(Colors.CYAN + "* " + s + Colors.NC);
    }

    public static final void error(final String s) {
        System.out.println(Colors.RED + "* " + s + Colors.NC);
    }

    public static final void success(final String s) {
        System.out.println(Colors.GREEN + "* " + s + Colors.NC);
    }
}
