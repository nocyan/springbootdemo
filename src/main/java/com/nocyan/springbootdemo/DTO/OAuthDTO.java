package com.nocyan.springbootdemo.DTO;

import com.nocyan.springbootdemo.enums.AuthTypeEnum;

public interface OAuthDTO {
    String getIdentifier();
    String getCredential();
    AuthTypeEnum getAuthType();
}
