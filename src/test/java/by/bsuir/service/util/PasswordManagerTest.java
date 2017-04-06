package by.bsuir.service.util;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class PasswordManagerTest {
    @Test
    public void passwordManagerTest(){
        PasswordManager psMng = PasswordManager.getInstance();
        byte[] wrongPass = "another".getBytes(StandardCharsets.UTF_8);
        byte[] hashed = psMng.encryptPassword("mysuperpuperpassword".getBytes(StandardCharsets.UTF_8));
        byte[] pass = "mysuperpuperpassword".getBytes(StandardCharsets.UTF_8);
        assertTrue(psMng.match(pass, hashed));
        assertFalse(psMng.match(wrongPass, hashed));
    }
}