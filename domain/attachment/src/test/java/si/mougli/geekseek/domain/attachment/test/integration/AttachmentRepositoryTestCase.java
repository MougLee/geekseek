package si.mougli.geekseek.domain.attachment.test.integration;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import static si.mougli.geekseek.domain.attachment.test.TestUtils.createAttachment;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import si.mougli.geekseek.domain.attachment.test.TestUtils;
import si.mougli.geekesek.domain.attachment.model.Attachment;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.test.integration.CoreDeployments;

@RunWith(Arquillian.class)
public class AttachmentRepositoryTestCase
{
    @Deployment
    public static WebArchive deployment()
    {
        return ShrinkWrap.create(WebArchive.class).addAsLibraries(CoreDeployments.core(), AttachmentDeployments.attachmentWithCache()).addAsLibraries(AttachmentDeployments.resolveDependencies()).addClass(TestUtils.class).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Repository<Attachment> repository;

    // Story: As a User I should be able to create an Attachment
    @Test
    public void shouldBeAbleToCreateAttachment()
    {
        Attachment attachment = createAttachment();
        repository.store(attachment);

        Attachment storedAttachment = repository.get(attachment.getId());
        Assert.assertNotNull(storedAttachment);

        Assert.assertEquals(attachment.getId(), storedAttachment.getId());
        Assert.assertEquals(attachment.getTitle(), storedAttachment.getTitle());
        Assert.assertEquals(attachment.getUrl(), storedAttachment.getUrl());
        Assert.assertEquals(attachment.getMimeType(), storedAttachment.getMimeType());
        Assert.assertNotNull(storedAttachment.getCreated());
    }

    // Story: As a User I should be able to update an Attachment
    @Test
    public void shouldBeAbleToUpdateAttachment()
    {
        String updatedTitle = "Test 2";
        Attachment attachment = createAttachment();
        attachment = repository.store(attachment);

        attachment = attachment.setTitle(updatedTitle);
        attachment = repository.store(attachment);

        Attachment updated = repository.get(attachment.getId());

        Assert.assertEquals(updatedTitle, updated.getTitle());
        Assert.assertNotNull(updated.getLastUpdated());
    }

    // Story: As a User I should be able to remove an Attachment
    @Test
    public void shouldBeAbleToRemoveAttachemtn()
    {
        Attachment attachment = createAttachment();
        attachment = repository.store(attachment);

        repository.remove(attachment);

        Attachment removed = repository.get(attachment.getId());
        Assert.assertNull(removed);
    }

    @Test
    public void shouldNotReflectNonStoredChanges()
    {
        String updatedTitle = "Test non stored changes";
        Attachment attachment = createAttachment();
        String originalTitle = attachment.getTitle();

        Attachment stored = repository.store(attachment);

        stored.setTitle(updatedTitle);

        Attachment refreshed = repository.get(attachment.getId());

        Assert.assertEquals(refreshed.getTitle(), originalTitle);
    }
}
