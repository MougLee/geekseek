package si.mougli.geekseek.domain.test.integration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import si.mougli.geekseek.domain.Repository;
import si.mougli.geekseek.domain.model.Identifiable;

/**
 *
 * @author mougli
 */
public class CoreDeployments
{
    public static JavaArchive core()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(false, Identifiable.class.getPackage(), Repository.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
