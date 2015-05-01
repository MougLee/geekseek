package si.mougli.geekseek.domain.relation.test.integration;

import java.io.File;
import javax.management.relation.Relation;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import si.mougli.geekseek.domain.relation.RelationRepository;
import si.mougli.geekseek.domain.relation.neo.GraphDatabaseProducer;

class RelationDeployments
{
    public static JavaArchive relation()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackages(false, Relation.class.getPackage(), RelationRepository.class.getPackage()).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public static JavaArchive relationWithNeo()
    {
        return relation().addPackage(GraphDatabaseProducer.class.getPackage());
    }

    public static File[] neo4j()
    {
        long start = System.currentTimeMillis();
        File[] result = Maven.resolver().loadPomFromFile("pom.xml").resolve("org.neo4j:neo4j").withTransitivity().asFile();
        System.out.println("Neo4j Resovled in " + (System.currentTimeMillis() - start) + " ms");

        return result;
    }
}
