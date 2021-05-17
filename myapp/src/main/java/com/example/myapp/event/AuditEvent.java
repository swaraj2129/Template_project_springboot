package com.example.myapp.event;

import org.springframework.context.ApplicationEvent;

public class AuditEvent<T> extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */

    // event
    private T data;

    // to the constructor we need to pass the event source
    public AuditEvent(T event) {
        super(event);
        this.data = event;

    }

    public T getData() {
        return data;
    }
}
