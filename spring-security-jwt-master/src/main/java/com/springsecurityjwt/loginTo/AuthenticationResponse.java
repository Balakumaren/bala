package com.springsecurityjwt.loginTo;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private int id;

    public AuthenticationResponse(String jwt,int id) {
        this.jwt = jwt;
        this.id= id;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJwt() {
        return jwt;
    }
}
