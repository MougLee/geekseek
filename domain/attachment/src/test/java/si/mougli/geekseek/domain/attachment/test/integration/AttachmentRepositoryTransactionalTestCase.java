package si.mougli.geekseek.domain.attachment.test.integration;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;
import si.mougli.geekesek.domain.attachment.model.Attachment;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.attachment.test.TestUtils;
import si.mougli.geekseek.domain.test.integration.BaseTransactionalSpecification;
import si.mougli.geekseek.domain.test.integration.CoreDeployments;

import static si.mougli.geekseek.domain.attachment.test.TestUtils.createAttachment;

@RunWith(Arquillian.class)
public class AttachmentRepositoryTransactionalTestCase extends BaseTransactionalSpecification<Attachment, Repository<Attachment>>
{
    private static final String UPDATED_TITLE = "TEST UPDATED";

    @Inject
    private Repository<Attachment> repository;

    public AttachmentRepositoryTransactionalTestCase()
    {
        super(Attachment.class);
    }

    // Given
    @Deployment
    public static WebArchive deploy()
    {
        return ShrinkWrap.create(WebArchive.class).addAsLibraries(CoreDeployments.core(), AttachmentDeployments.attachmentWithCache()).addAsLibraries(AttachmentDeployments.resolveDependencies()).addClasses(BaseTransactionalSpecification.class, TestUtils.class).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Override
    protected Repository<Attachment> getRepository()
    {
        return repository;
    }

    @Override
    protected Attachment createNewDomainObject()
    {
        return createAttachment();
    }

    @Override
    protected Attachment updateDomainObject(Attachment domain)
    {
        return domain.setTitle(UPDATED_TITLE);
    }

    @Override
    protected void validateUpdatedDomainObject(Attachment domain)
    {
        Assert.assertEquals(UPDATED_TITLE, domain.getTitle());
    }
}
