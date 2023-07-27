package com.mylearning.domain.event.publisher;

import com.mylearning.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent>{
    void publish (T domainEvent);
}
