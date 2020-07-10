package api.test.repository;

import api.test.model.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;
import io.reactivex.Maybe;

@Repository
public interface UserRepository extends RxJavaCrudRepository<User, Long> {

    Maybe<User> findByEmail(String email);

}
