package si.mougli.geekseek.domain.persistence.test.integration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.persistence10.PersistenceDescriptor;
import si.mougli.geekseek.domain.persistence.PersistenceRepository;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;

public class PersistenceDeployments
{
    public static JavaArchive persistence()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .merge(model())
                .merge(repository());
    }

    public static JavaArchive model()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BaseEntity.class.getPackage());
    }

    public static JavaArchive repository()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PersistenceRepository.class.getPackage());
    }

    public static PersistenceDescriptor descriptor()
    {
        return Descriptors.create(PersistenceDescriptor.class)
                .createPersistenceUnit().name("test")
                .getOrCreateProperties()
                .createProperty().name("hibernate.hbm2ddl.auto").value("create-drop").up()
                .createProperty().name("hibernate.show_sql").value("true").up().up()
                .jtaDataSource("java:jboss/datasources/ExampleDS").up();
    }
}
