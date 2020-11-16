package com.raptor.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.domain
 * @date 2020/11/5 10:21
 */
@Data
public class Payload<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
