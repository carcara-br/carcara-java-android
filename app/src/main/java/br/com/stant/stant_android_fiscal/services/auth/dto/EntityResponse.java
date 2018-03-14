package br.com.stant.stant_android_fiscal.services.auth.dto;

/**
 * Created by denisvieira on 18/05/17.
 */

public class EntityResponse {

    private final int id;
    private final String name;
    private final String state;

    public EntityResponse(int id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
