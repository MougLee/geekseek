package si.mougli.geekseek.domain.attachment.test.integration;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import si.mougli.geekesek.domain.attachment.AttachmentRepository;
import si.mougli.geekesek.domain.attachment.infinispan.CacheProducer;
import si.mougli.geekesek.domain.attachment.model.Attachment;

/**
 * @author matic
 */
public class AttachmentDeployments
{
    public static JavaArchive attachment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Attachment.class.getPackage()).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public static JavaArchive attachmentWithCache()
    {
        return attachment().addPackage(AttachmentRepository.class.getPackage()).addPackage(CacheProducer.class.getPackage());
    }

    public static File[] resolveDependencies()
    {
        return Maven.resolver().offline().loadPomFromFile("pom.xml").resolve("org.infinispan:infinispan-core").withTransitivity().asFile();
    }
}
