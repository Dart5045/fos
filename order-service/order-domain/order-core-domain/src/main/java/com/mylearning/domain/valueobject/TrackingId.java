package com.mylearning.domain.valueobject;

import java.util.UUID;

public class TrackingId extends BaseId<UUID>{

    public TrackingId(UUID uuid) {
        super(uuid);
    }
}
