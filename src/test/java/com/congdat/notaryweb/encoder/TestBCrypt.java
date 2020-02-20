package com.congdat.notaryweb.encoder;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
    @Test
    public void matchesBcrypt() {

        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
