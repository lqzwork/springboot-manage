package com.baixin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : ScpConfigProperties
 * @Author : liqz
 * @desc : scp传输参数配置
 * @Date : 2020/01/09 16:17
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "check.files")
public class ConfigProperties {
    /**
     * 打包后名称
     */
    private String zipName;
    /**
     * 服务器上传目录
     */
    private String toPath;
}
