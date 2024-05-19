package lpm.Abstract;

public interface AbstractManager {
    /**
     * Install a package.
     * 
     * @param name
     */
    public void install(String name);

    /**
     * Remove a package.
     * 
     * @param name
     */
    public void remove(String name);

    /**
     * Run a package.
     * 
     * @param name
     */
    public void run(String name);

    /**
     * Refresh the package list.
     */
    public void refresh();

    /**
     * Throw an error when installing a package.
     */
    public void throwInstallError();
}
