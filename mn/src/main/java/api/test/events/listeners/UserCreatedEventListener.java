package api.test.events.listeners;

import api.test.client.UserClient;
import api.test.events.UserCreatedEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
public class UserCreatedEventListener {

    @Inject
    UserClient client;

    @EventListener
    public void userCreated(@NotNull UserCreatedEvent event) {
        client.send(event.getUser());
    }
}