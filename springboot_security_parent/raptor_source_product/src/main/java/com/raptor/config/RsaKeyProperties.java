package com.raptor.config;

import com.raptor.utils.RsaUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

/**
 * @author raptor
 * @version V1.0
 * @Package config
 * @date 2020/11/5 11:20
 */

@Configuration
@ConfigurationProperties("rsa.key")
public class RsaKeyProperties {
    private String pubKeyFile;

    private PublicKey publicKey;

    //在什么什么之后构造
    @PostConstruct
    public void creatRsaKey() throws Exception {
        publicKey= RsaUtils.getPublicKey(pubKeyFile);
    }
    public String getPubKeyFile() {
        return pubKeyFile;
    }

    public void setPubKeyFile(String pubKeyFile) {
        this.pubKeyFile = pubKeyFile;
    }


    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
