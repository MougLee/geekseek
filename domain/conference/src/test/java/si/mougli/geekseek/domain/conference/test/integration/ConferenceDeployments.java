package si.mougli.geekseek.domain.conference.test.integration;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import si.mougli.geekseek.domain.conference.ConferenceRepository;
import si.mougli.geekseek.domain.conference.model.Conference;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;
import si.mougli.geekseek.domain.test.integration.CoreDeployments;

public class ConferenceDeployments
{
    public static JavaArchive conference()
    {
        return CoreDeployments.core()
                .merge(domain())
                .merge(persistenceRepository());
    }

    public static JavaArchive domain()
    {
        return CoreDeployments.core()
                .addPackage(Conference.class.getPackage())
                .addPackage(BaseEntity.class.getPackage());
    }

    public static JavaArchive repository()
    {
        return CoreDeployments.core().addPackage(ConferenceRepository.class.getPackage());
    }

    public static JavaArchive persistenceRepository()
    {
        return repository().addPackages(false, PersistenceRepository.class.getPackage());
    }
}
