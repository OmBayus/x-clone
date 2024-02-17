package com.osntus.xserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.osntus.xserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    private User user;
}
