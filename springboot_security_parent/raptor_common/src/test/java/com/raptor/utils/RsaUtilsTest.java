package com.raptor.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.utils
 * @date 2020/11/5 10:50
 */
public class RsaUtilsTest {

    private String privateKeyPath="G:\\resources\\springboot_demo\\spring_security\\springboot_security_parent\\raptor_common\\id_key_rsa";
    private String publicKeyPath="G:\\resources\\springboot_demo\\spring_security\\springboot_security_parent\\raptor_common\\id_key_rsa.pub";

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicKeyPath,privateKeyPath,"raptor",2048);
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicKeyPath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateKeyPath));
    }


}