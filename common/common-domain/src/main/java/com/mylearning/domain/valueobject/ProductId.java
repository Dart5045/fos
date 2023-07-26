package com.mylearning.domain.valueobject;

import java.util.UUID;

public class ProductId<UUID> extends BaseId<UUID> {
    public ProductId(UUID uuid) {
        super(uuid);
    }
}
