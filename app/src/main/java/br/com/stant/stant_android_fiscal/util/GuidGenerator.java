package br.com.stant.stant_android_fiscal.util;

import java.util.UUID;

/**
 * Created by rachidcalazans on 11/18/16.
 */
public class GuidGenerator {

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
