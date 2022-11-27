package seapa.back.Audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("SYSTEM").filter(s -> !s.isEmpty());
    }
}
