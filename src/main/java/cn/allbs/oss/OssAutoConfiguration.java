package cn.allbs.oss;

import cn.allbs.oss.http.OssEndpoint;
import cn.allbs.oss.properties.OssProperties;
import cn.allbs.oss.service.OssTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 类 OssAutoConfiguration
 * </p>
 *
 * @author ChenQi
 * @since 2022/11/4 13:48
 */
@EnableConfigurationProperties({OssProperties.class})
public class OssAutoConfiguration {

    /**
     * OSS操作模板
     *
     * @return OSS操作模板
     */
    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    @ConditionalOnProperty(prefix = OssProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
    public OssTemplate ossTemplate(OssProperties properties) {
        return new OssTemplate(properties);
    }

    /**
     * OSS端点信息
     *
     * @param template oss操作模版
     * @return oss远程服务端点
     */
    @Bean
    @ConditionalOnWebApplication
    @ConditionalOnProperty(prefix = OssProperties.PREFIX, name = "http.enable", havingValue = "true")
    public OssEndpoint ossEndpoint(OssTemplate template) {
        return new OssEndpoint(template);
    }

}
