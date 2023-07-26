package com.mylearning.domain.valueobject;

public class CustomerId<UUID> extends BaseId<UUID> {
    public CustomerId(UUID uuid) {
        super(uuid);
    }
}
