package com.birrete.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BirreteAuthResponse {
    private String result;
    private String username;
    private String token;


    public static BirreteAuthResponse getAuthorizedResponse(String username, String token){
        BirreteAuthResponse response = new BirreteAuthResponse();
        response.setResult("AUTH");
        response.setUsername(username);
        response.setToken(token);
        return response;
    }

    public static BirreteAuthResponse getErrorResponse(){
        BirreteAuthResponse response = new BirreteAuthResponse();
        response.setResult("ERROR");
        return response;
    }
}
