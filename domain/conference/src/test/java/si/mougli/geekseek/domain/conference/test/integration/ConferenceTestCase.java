package si.mougli.geekseek.domain.conference.test.integration;

import java.io.File;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import si.mougli.geekseek.domain.conference.ConferenceRepository;
import si.mougli.geekseek.domain.conference.model.Conference;
import si.mougli.geekseek.domain.conference.model.Duration;
import si.mougli.geekseek.domain.conference.test.TestUtils;
import si.mougli.geekseek.domain.core.Created;
import si.mougli.geekseek.domain.core.Removed;
import si.mougli.geekseek.domain.persistence.test.integration.PersistenceDeployments;

import static si.mougli.geekseek.domain.conference.test.TestUtils.toDate;

/**
 * @author mougli
 */
@Transactional(TransactionMode.COMMIT)
@RunWith(Arquillian.class)
public class ConferenceTestCase
{
    @Inject
    private ConferenceRepository repository;

    // these fields are static because Events observed by this TestClass
    // are not observed on the same TestClass instance as @Test is running.
    private static boolean createdEventFired = false;
    private static boolean removedEventFired = false;

    @Deployment
    public static WebArchive deploy()
    {
        return ShrinkWrap.create(WebArchive.class).addAsLibraries(ConferenceDeployments.conference().addClasses(ConferenceTestCase.class, TestUtils.class).addAsManifestResource(new StringAsset(PersistenceDeployments.descriptor().exportAsString()), "persistence.xml").addAsManifestResource(new File("src/main/resources/META-INF/beans.xml"))).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public void createdEventFired(@Observes @Created Conference conference)
    {
        createdEventFired = true;
    }

    public void removedEventFired(@Observes @Removed Conference conference)
    {
        removedEventFired = true;
    }

    // Story: As a User I should be able to create a Conference
    @Test
    @ShouldMatchDataSet(value = "conference.yml", excludeColumns = "*id")
    public void shouldBeAbleToCreateConference()
    {
        Conference conference = createConference();
        System.out.println("TEEEEEST!!");
        repository.store(conference);
        Assert.assertTrue(createdEventFired);
    }

    public static Conference createConference()
    {
        final Duration duration = new Duration(toDate(2013, 11, 11), toDate(2013, 11, 15));
        return new Conference("Devoxx Belgium 2014", "We Code In Peace", duration);
    }
}
