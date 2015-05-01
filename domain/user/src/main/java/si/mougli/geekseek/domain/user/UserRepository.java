package si.mougli.geekseek.domain.user;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;
import si.mougli.geekseek.domain.user.model.User;

@Stateless
@LocalBean
@Typed(UserRepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRepository extends PersistenceRepository<User>
{
    public UserRepository()
    {
        super(User.class);
    }
}
