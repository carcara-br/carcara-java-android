package br.com.stant.stant_android_fiscal.services.auth.dto;

/**
 * Created by denisvieira on 10/05/17.
 */

public class AuthRequest {
    private String email;
    private String password;
    private String device_id;
    private String device_type;

    public AuthRequest(String email, String password, String device_id) {
        this.email        = email;
        this.password     = password;
        this.device_id    = device_id;
        this.device_type  = "android";
    }

    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public String getDeviceId() { return device_id; }

    public String getDeviceType() { return device_type; }
}
