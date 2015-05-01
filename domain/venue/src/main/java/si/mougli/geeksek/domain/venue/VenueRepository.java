package si.mougli.geeksek.domain.venue;

import javax.ejb.Stateless;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;
import si.mougli.geeksek.domain.venue.model.Venue;

@Stateless
public class VenueRepository extends PersistenceRepository<Venue>
{
    public VenueRepository()
    {
        super(Venue.class);
    }
}
