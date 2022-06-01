package com.kalsym.easyrider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    String location;
    Double Price;
    String status;
    String message;
    String transactionId;

}
