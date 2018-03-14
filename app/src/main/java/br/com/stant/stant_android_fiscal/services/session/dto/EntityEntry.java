package br.com.stant.stant_android_fiscal.services.session.dto;

import com.github.brunodles.simplepreferences.lib.Property;

/**
 * Created by denisvieira on 18/05/17.
 */

public class EntityEntry {
    public static final String PREFERENCE_KEY = "EntityEntry";

    @Property public int id;
    @Property public String name;
    @Property public String state;
}
