package com.event;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class EventBus {
    private static final EventBus INSTANCE = new EventBus();

    private final PublishSubject<Event> mBusSubject = PublishSubject.create();

    public static EventBus getInstance() {
        return INSTANCE;
    }

    public Disposable register(Consumer<Event> onNext) {
        return mBusSubject.subscribe(onNext);
    }

    public void postSave(Event event) {
        mBusSubject.onNext(event);
    }
}

