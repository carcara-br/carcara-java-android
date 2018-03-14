package br.com.stant.stant_android_fiscal.services.session.dto;

import com.github.brunodles.simplepreferences.lib.Property;

/**
 * Created by rachidcalazans on 3/13/17.
 */

public class UserSessionEntry {
    public static final String PREFERENCE_KEY = "UserSessionEntry";

    @Property public String token;
    @Property public String token_type;
    @Property public int id;
    @Property public String name;
    @Property public EntityEntry entity;
    @Property public String image_profile;
    @Property public String created_at;
    @Property public String device_id;
}
