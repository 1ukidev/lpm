package lpm.Abstract;

public interface AbstractWeb {
    /**
     * Download a file.
     * 
     * @param url
     * @param outputFile
     */
    public int get(String url, String outputFile);
}
