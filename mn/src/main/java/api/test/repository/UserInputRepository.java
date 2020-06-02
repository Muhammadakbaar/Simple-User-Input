package api.test.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;
import java.util.Date;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import api.test.model.UserInput;
import javax.persistence.EntityManager;



@Singleton
public class UserInputRepository implements UserInputInterface{
    @PersistenceContext
    private EntityManager manager;

    public UserInputRepository(@CurrentSession EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserInput> findAll(int page, int limit) {
        TypedQuery<UserInput> query = manager
            .createQuery("from UserInput", UserInput.class)
            .setFirstResult(page > 1 ? page * limit - limit : 0 )
            .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional
    public String save(@NotNull UserInput userInput) {
        try {
            manager.persist(userInput);
            return "{\"status\":\"ok\"}";
        } catch (Exception e) {
            return "{\"status\":\"fail\", \"message\": \""+e.getMessage()+"\"}";
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Long size() {
        return manager.createQuery("select count(*) from UserInput", Long.class).getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public UserInput findById(@NotNull Long id) {
        return manager.find(UserInput.class, id);
    }

    @Transactional
    @Override
    public boolean update(@NotNull Long id, String user_name, String user_password) {
        try {
            UserInput userInput = manager.find(UserInput.class, id);
            if(user_name != null) userInput.setUser_name(user_name);
            if(user_password != null) userInput.setUser_password(user_password);
            
            userInput.setUpdated_at(new Date());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean destroy(@NotNull Long id) {
        try {
            UserInput userInput = manager.find(UserInput.class, id);
            manager.remove(userInput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



   
}

