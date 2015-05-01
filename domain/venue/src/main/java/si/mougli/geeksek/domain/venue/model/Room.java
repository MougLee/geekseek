package si.mougli.geeksek.domain.venue.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;

@Entity
public class Room extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    public Room()
    {
        super(UUID.randomUUID().toString());
    }

    public String getName()
    {
        return name;
    }
}
