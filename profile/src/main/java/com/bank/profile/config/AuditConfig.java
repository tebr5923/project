package com.bank.profile.config;

import com.bank.profile.util.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Класс конфигурации, включает поддержку аудита через аннотацию @EnableJpaAuditing.
 */
@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }
}
