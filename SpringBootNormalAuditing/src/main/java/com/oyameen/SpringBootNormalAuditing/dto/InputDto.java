package com.oyameen.SpringBootNormalAuditing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class InputDto <T>{
    @JsonProperty(required = true, value = "userId")
    private String authenticatedUser;
    private T entityModel;
}
