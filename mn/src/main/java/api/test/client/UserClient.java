package api.test.client;

import api.test.model.User;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient("micronaut")
public interface UserClient {

    @Binding("users")
    void send(User user);

}