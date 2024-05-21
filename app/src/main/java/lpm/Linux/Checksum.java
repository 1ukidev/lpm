package lpm.Linux;

import java.io.File;
import java.io.IOException;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;

public class Checksum {
    public static final String generate(final File file) throws IOException {
        return Files.asByteSource(file).hash(Hashing.sha256()).toString();
    }
}
