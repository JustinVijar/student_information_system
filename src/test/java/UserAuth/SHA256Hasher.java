package UserAuth;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class SHA256Hasher {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256", "BC");
            return digest.digest(input.getBytes());
        } catch (NoSuchAlgorithmException | java.security.NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "Hello, Bouncy Castle!";

        byte[] hashedBytes = hashString(input);

        // Print the hashed bytes (in hexadecimal format)
        System.out.print("Hashed Bytes (in hexadecimal format): ");
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.length());
    }
}
