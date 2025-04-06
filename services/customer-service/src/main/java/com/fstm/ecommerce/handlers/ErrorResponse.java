package com.fstm.ecommerce.handlers;


import java.util.Map;

public record ErrorResponse (
        Map<String,String> errors
){
}
