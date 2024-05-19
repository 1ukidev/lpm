package lpm.Abstract;

public interface AbstractShell {
    /**
     * Execute a command.
     * 
     * @param workingDir
     * @param cmd
     */
    public int exec(String workingDir, String... cmd);
}
