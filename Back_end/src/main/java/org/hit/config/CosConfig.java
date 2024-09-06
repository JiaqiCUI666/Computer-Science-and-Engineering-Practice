package org.hit.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author phf
 * @description @ConfigurationProperties 松散绑定（中划线-、下划线_ 都去掉,且不区分大小写）
 */
@Component
@ConfigurationProperties(prefix = "cos")
@Data
public class CosConfig {
    /**
     * 腾讯云账号秘钥
     */
    private String secretId="x";
    /**
     * 密码秘钥
     */
    private String secretKey="x";
    /**
     * 存储桶地区
     */
    private String region="x";
    /**
     * 存储桶名称
     */
    private String bucketName="x";
    /**
     * 存储桶访问路径
     */
    private String path="/";

    /**
     * 初始化cos对象,配置相关配置信息
     */
    @Bean
    public COSClient cosClient(){
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(this.secretId, this.secretKey);
        // 2 设置 bucket 的区域
        Region region = new Region(this.region);
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }
}
