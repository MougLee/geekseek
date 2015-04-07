package si.mougli.geekseek.domain.conference.model;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;

@Entity
public class Session extends BaseEntity
{

    private static final long serialVersionUID = 1L;

    @Embedded
    private Duration duration;

    private String title;

    @Lob
    private String outline;

    @ManyToOne
    private Conference conference;

    // JPA
    protected Session()
    {
    }

    public Session(@NotNull String title, @NotNull String outline, @NotNull Duration duration)
    {
        super(UUID.randomUUID().toString());
//        requireNonNull(title, "Title must be specified");
//        requireNonNull(outline, "Outline must be specified");
//        requireNonNull(duration, "Duration must be specified");
        this.title = title;
        this.outline = outline;
        this.duration = duration;
    }

    public Duration getDuration()
    {
        return duration;
    }

    public Session setDuration(@NotNull Duration duration)
    {
//        requireNonNull(duration, "Duration must be specified");
        this.duration = duration;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public Session setTitle(@NotNull String title)
    {
//        requireNonNull(title, "Title must be specified");
        this.title = title;
        return this;
    }

    public String getOutline()
    {
        return outline;
    }

    public Session setOutline(@NotNull String outline)
    {
//        requireNonNull(outline, "Outline must be specified");
        this.outline = outline;
        return this;
    }

    public Conference getConference()
    {
        return conference;
    }

    void setConference(Conference conference)
    {
        this.conference = conference;
    }

    @PreRemove
    public void removeConferenceRef()
    {
        if (conference != null)
        {
            conference.removeSession(this);
        }
    }
}
