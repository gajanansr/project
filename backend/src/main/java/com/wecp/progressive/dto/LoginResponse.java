package com.wecp.progressive.dto;

public class LoginResponse {
   private String token;
   private String roles;
   private Integer userId;

   public LoginResponse(String token, String roles, Integer userId) {
      this.token = token;
      this.roles = roles;
      this.userId = userId;
   }

   public String getToken(){
      return token;
   }

   public void setToken(String token){
      this.token = token;
   }

   public String getRoles()
   {
      return roles;
   }

   public void setRole(String roles)
   {
      this.roles = roles;
   }

   public long getUserId()
   {
      return userId;
   }

   public void setUserId(Integer userId)
   {
      this.userId = userId;
   }

}
