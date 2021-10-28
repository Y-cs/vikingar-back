package self.vikingar.util;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 15:13
 * @Description:
 **/
public enum FileHashUtil {

    /**
     * MD5
     */
    MD5("MD5"),
    /**
     * SHA1
     */
    SHA1("SHA1"),
    /**
     * SHA-256
     */
    SHA256("SHA-256"),
    /**
     * SHA-512
     */
    SHA512("SHA-512");
    /**
     * 名称
     */
    private final String name;

    FileHashUtil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String checksum(byte[] bytes) throws IOException {
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            MessageDigest digest = MessageDigest.getInstance(getName());
            byte[] block = new byte[4096];
            int length;
            while ((length = in.read(block)) > 0) {
                digest.update(block, 0, length);
            }
            return DatatypeConverter.printHexBinary(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
