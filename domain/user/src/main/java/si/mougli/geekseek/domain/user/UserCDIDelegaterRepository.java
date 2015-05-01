package si.mougli.geekseek.domain.user;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.user.model.User;

@ApplicationScoped
public class UserCDIDelegaterRepository implements Repository<User>
{
    @EJB
    private UserRepository repo;

    public Class<User> getType()
    {
        return repo.getType();
    }

    public User store(User entity)
    {
        return repo.store(entity);
    }

    public User get(String id)
    {
        return repo.get(id);
    }

    public void remove(User entity)
    {
        repo.remove(entity);
    }
}
