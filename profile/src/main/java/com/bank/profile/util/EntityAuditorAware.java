package com.bank.profile.util;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Util класс, настраивает пользователя для полей createdBy и modifiedBy в аудите.
 */
public class EntityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Ivan Pereverzev");
    }
}
