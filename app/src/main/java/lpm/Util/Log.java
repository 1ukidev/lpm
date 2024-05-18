package lpm.Util;

public class Log {
    public static final void info(String s) {
        System.out.println(Colors.CYAN + "* " + s + Colors.NC);
    }

    public static final void error(String s) {
        System.out.println(Colors.RED + "* " + s + Colors.NC);
    }

    public static final void success(String s) {
        System.out.println(Colors.GREEN + "* " + s + Colors.NC);
    }
}
