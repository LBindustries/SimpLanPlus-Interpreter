package util;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class AsmChecksum {
    // Simple utility that calculates and compares CRC32 checksums.
    public CRC32 checksum;
    public AsmChecksum(String contents){
        byte[] bytes = contents.getBytes();
        this.checksum = new CRC32();
        this.checksum.update(bytes, 0, bytes.length);
    }

    public boolean isEqual(long other){
        return this.checksum.getValue() == other;
    }
}
