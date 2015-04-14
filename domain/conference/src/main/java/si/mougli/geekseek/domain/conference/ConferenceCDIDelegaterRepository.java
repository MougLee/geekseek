package si.mougli.geekseek.domain.conference;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.conference.model.Conference;

@ApplicationScoped
public class ConferenceCDIDelegaterRepository implements Repository<Conference>//, SearchableRepository<Conference, ConferenceCriteria>
{

    @EJB
    private ConferenceRepository repo;

    @Override
    public Class<Conference> getType()
    {
        return repo.getType();
    }

    @Override
    public Conference store(Conference entity)
    {
        return repo.store(entity);
    }

    @Override
    public Conference get(String id)
    {
        return repo.get(id);
    }

    @Override
    public void remove(Conference entity)
    {
        repo.remove(entity);
    }

//    @Override
//    public Collection<Conference> search(ConferenceCriteria criteria)
//    {
//        return repo.search(criteria);
//    }
}
