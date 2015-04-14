package si.mougli.geekseek.domain.conference;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Typed;
import si.mougli.geekseek.domain.conference.model.Conference;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;

/**
 * This EJB is @Typed to a specific type to avoid being picked up by CDI under
 * Repository<Conference> due to limitations/error in the CDI EJB interactions.
 * A EJB Beans is always resolved as Repository<T>, which means two EJBs that
 * implements the Repository interface both respond to the InjectionPoint
 *
 * @Inject Repository<X> and making the InjectionPoint ambiguous.
 *
 * As a WorkAround we wrap the EJB that has Transactional properties in CDI bean
 * that can be used by the Type system. The EJB is to be considered a internal
 * implementation detail. The CDI Type provided by the
 * ConferenceCDIDelegateRepository is the real Repository api.
 *
 * In other words - with @Typed annotation we make CDI only see this bean when
 * we use @Inject ConferenceRepository so when we use @Inject
 * Repository<Conference> this bean is not picked by CDI so we avoid getting an
 * ambiguous injection error.
 */
@Stateless
@LocalBean
@Typed(ConferenceRepository.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ConferenceRepository extends PersistenceRepository<Conference>
{
    public ConferenceRepository()
    {
        super(Conference.class);
    }
}
