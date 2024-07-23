package com.tiger.customer.configs.audit;

import com.tiger.customer.utils.UserInfoUtil;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public @NonNull Optional<String> getCurrentAuditor() {
        return Optional.of(
                UserInfoUtil.getAccountUser() == null
                        ? "system"
                        : UserInfoUtil.getAccountUser().getUsername());
    }
}
