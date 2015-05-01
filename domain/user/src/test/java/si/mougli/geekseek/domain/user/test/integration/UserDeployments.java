package si.mougli.geekseek.domain.user.test.integration;

import org.jboss.shrinkwrap.api.spec.JavaArchive;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;
import si.mougli.geekseek.domain.test.integration.CoreDeployments;
import si.mougli.geekseek.domain.user.UserRepository;
import si.mougli.geekseek.domain.user.model.User;

public class UserDeployments
{
    public static JavaArchive user()
    {
        return CoreDeployments.core()
                .merge(domain())
                .merge(persistenceRepository());
    }

    public static JavaArchive domain()
    {
        return CoreDeployments.core()
                .addPackage(User.class.getPackage())
                .addPackage(BaseEntity.class.getPackage());
    }

    public static JavaArchive repository()
    {
        return CoreDeployments.core().addPackage(UserRepository.class.getPackage());
    }

    public static JavaArchive persistenceRepository()
    {
        return repository().addPackages(false, PersistenceRepository.class.getPackage());
    }
}
