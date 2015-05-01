package si.mougli.geekseek.domain.user.test.integration;

import java.io.File;
import java.util.UUID;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.persistence.test.integration.PersistenceDeployments;
import si.mougli.geekseek.domain.test.integration.BaseTransactionalSpecification;
import si.mougli.geekseek.domain.user.model.User;

@RunWith(Arquillian.class)
public class UserRepositoryTransactionalTestCase extends BaseTransactionalSpecification<User, Repository<User>>
{
    private static final String UPDATED_NAME = "TEST UPDATED";

    public UserRepositoryTransactionalTestCase()
    {
        super(User.class);
    }

    // Given
    @Deployment
    public static WebArchive deploy()
    {
        return ShrinkWrap.create(WebArchive.class).addAsLibraries(UserDeployments.user().addAsManifestResource(new StringAsset(PersistenceDeployments.descriptor().exportAsString()), "persistence.xml").addAsManifestResource(new File("src/main/resources/META-INF/beans.xml"))).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml").addClass(BaseTransactionalSpecification.class);
    }

    @Inject
    private Repository<User> repository;

    @Override
    protected User createNewDomainObject()
    {
        return new User(UUID.randomUUID().toString()).setBio("Bio");
    }

    @Override
    protected User updateDomainObject(User domain)
    {
        return domain.setName(UPDATED_NAME);
    }

    @Override
    protected void validateUpdatedDomainObject(User domain)
    {
        Assert.assertEquals(UPDATED_NAME, domain.getName());
    }

    @Override
    protected Repository<User> getRepository()
    {
        return repository;
    }
}
