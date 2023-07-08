package com.example.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YamlConfignature {
  public static String Access_Control_Allow_Origin;

  @Value("${CORS.Access-Control-Allow-Origin}")
  public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
    Access_Control_Allow_Origin = accessControlAllowOrigin;
  }
}
