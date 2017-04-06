package by.bsuir.service.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Provides caching and comparing the cached password
 */
public class PasswordManager {
    private static final Logger logger = Logger.getLogger(PasswordManager.class);
    private static final int SALT_SIZE = 16;
    private static final String SALT_ALGORITHM = "SHA1PRNG";
    private static final String ENCRYPT_ALGORITHM = "SHA-512";
    private static final PasswordManager INSTANCE = new PasswordManager();

    private PasswordManager() {}

    /**
     * Returns {@link PasswordManager} instance
     * @return  PasswordManager instance
     */
    public static PasswordManager getInstance() {
        return INSTANCE;
    }

    /**
     * Generates a salt for making password stronger
     * @return massive of random bytes
     */
    private byte[] getSalt(){
        try {
            SecureRandom sr = SecureRandom.getInstance(SALT_ALGORITHM);
            byte[] salt = new byte[SALT_SIZE];
            sr.nextBytes(salt);
            return salt;
        } catch(NoSuchAlgorithmException e){
            logger.error(e);
        }
        return null;
    }

    /**
     * Encrypts a given password with salt.
     * After encrypting given password will be filled zero bytes.
     * @param password byte array for encrypting
     * @param salt byte array for making cached password
     * @return encrypted password like a byte array
     */
    private byte[] encryptPassword(byte[] password, byte[] salt){
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPT_ALGORITHM);
            md.update(salt);
            byte[] bytes = md.digest(password);
            byte[] generatedPassword = new byte[bytes.length + SALT_SIZE];
            System.arraycopy(salt, 0, generatedPassword, 0, salt.length);
            for(int i = 0; i < bytes.length; i++){
                generatedPassword[i + SALT_SIZE] = (byte) ((bytes[i] & 0xFF) + 0x100);
            }
            PasswordManager.dispose(bytes, password, salt);
            return generatedPassword;
        }
        catch(NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * Encrypts a given password.
     * After encrypting given password will be filled zero bytes.
     * @param password byte array for encrypting
     * @return encrypted password like a byte array
     */
    public byte[] encryptPassword(byte[] password){
        return this.encryptPassword(password, this.getSalt());
    }

    /**
     * Compares real password byte array with given cached password.
     * After encrypting given password and hashed password will be filled zero bytes.
     * @param password real bytes, which wasn't cached
     * @param hashedPassword cached password
     * @return {@code true} if this passwords compare and {@code false} otherwise
     */
    public boolean match(byte[] password, byte[] hashedPassword){
        byte[] salt = new byte[SALT_SIZE];
        System.arraycopy(hashedPassword, 0, salt, 0, SALT_SIZE);

        byte[] generatedPassword = encryptPassword(password, salt);
        boolean answer = Arrays.equals(generatedPassword, hashedPassword);
        PasswordManager.dispose(generatedPassword, hashedPassword);

        return answer;
    }

    /**
     * Disposes of the data arrays (filling them with zero)
     * @param arrays for disposing
     */
    public static void dispose(byte[]... arrays){
        for(byte[] password : arrays){
            if(password != null){
                Arrays.fill(password, (byte) 0);
            }
        }
    }
}
