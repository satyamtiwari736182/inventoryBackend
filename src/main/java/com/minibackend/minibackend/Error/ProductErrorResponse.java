package com.minibackend.minibackend.Error;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ProductErrorResponse {
    private String msg;
    private int status;
    private long timeStamp;
}
