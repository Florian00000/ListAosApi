package com.florian.aos.battlescrollservice.exception;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class ConstraintMessageResolver {

    private final Map<String, String> constraintMessages;

    public ConstraintMessageResolver() {
        this.constraintMessages = Map.of(
                "uc_version_name", "This Version name is already used",
                "uc_charter_name", "This Charter name is already used"
        );
    }

    public Optional<String> resolveMessage(String dbErrorMessage) {
        return constraintMessages.entrySet().stream()
                .filter(entry -> dbErrorMessage.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}

