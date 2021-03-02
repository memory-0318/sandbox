package tw.brian.config.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Brian Su <brian.su@tpisoftware.com>
 * @description: Spring Validation若要啟用Resource Bundle i18n多語系支援，須設定MessageSource與LocalValidatorFactoryBean
 * @date: 2021/2/9
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        // 設定Validation訊息設定檔來源
        messageSource.setBasename("classpath:validation/messages");
        // 設定Validation訊息設定檔文字編碼
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(this.messageSource());
        return bean;
    }
}
