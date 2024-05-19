package lpm.Util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lpm.Abstract.AbstractWeb;

public class Web implements AbstractWeb {
    @SuppressWarnings("deprecation")
    public final int get(String url, String outputFile) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() != 200) {
                Log.error("Failed to download file: " + connection.getResponseMessage());
                return 1;
            }

            inputStream = connection.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            fileOutputStream = new FileOutputStream(outputFile);

            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(data, 0, 1024)) != -1) {
                fileOutputStream.write(data, 0, bytesRead);
            }
            
            return 0;
        } catch (Exception e) {
            Log.error("Failed to download file: " + e.getMessage());
            return 1;
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception e) {
                Log.error("Failed to close streams: " + e.getMessage());
                return 1;
            }
        }
    }
}
