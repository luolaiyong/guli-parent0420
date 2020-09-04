package com.atguigu.ossservice.utils;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {


    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    @Value("${aliyun.oss.file.filehost}")
    private String filehost;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FILE_HOST;

    @Override
    public void afterPropertiesSet() throws Exception {

        END_POINT =endpoint;
        ACCESS_KEY_ID =keyid;
        ACCESS_KEY_SECRET = keysecret;
        BUCKET_NAME = bucketname;
        FILE_HOST = filehost;
    }

    public static void main(String[] args) {
        System.out.println(END_POINT);
        System.out.println(new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET));

    }
}
