package si.mougli.geekseek.domain.conference;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;
import si.mougli.geekseek.domain.conference.model.Conference;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;

/**
 *
 * @author mougli
 */
@Stateless
@LocalBean
@Typed(ConferenceRepository.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ConferenceRepository extends PersistenceRepository<Conference>
{
    public ConferenceRepository()
    {
        super(Conference.class);
    }
}
