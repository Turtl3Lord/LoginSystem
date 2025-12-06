package com.BackEnd.DTO.JWT;

public class TokenResponse {
    private String accessToken;
    private String tokenType;
 //   private String refreshToken;
 //   private String scope;

    public TokenResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
  
    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

   

    
}
