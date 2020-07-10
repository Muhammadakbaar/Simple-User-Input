package api.test.events;
import api.test.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserCreatedEvent {

    @NotNull
    private User user;

}

