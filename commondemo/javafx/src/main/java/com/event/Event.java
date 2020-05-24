package com.event;

public class Event {
    private EventType type;

    public Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public static enum EventType {
        MDlMenuEvent,
        CDTMenuEvent,
        CRSMenuEvent,
        FloorMapMenuEvent,
        DBMenuEvent
    }
}
