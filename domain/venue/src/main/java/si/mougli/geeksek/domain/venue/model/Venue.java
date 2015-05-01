package si.mougli.geeksek.domain.venue.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import si.mougli.geekseek.domain.persistence.model.BaseEntity;

@Entity
public class Venue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @Valid
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Room> rooms;

    public Venue()
    {
        super(UUID.randomUUID().toString());
    }

    public Set<Room> getRooms()
    {
        return Collections.unmodifiableSet(rooms);
    }

    public Venue addRoom(Room room)
    {
        if (rooms == null)
        {
            this.rooms = new HashSet<>();
        }
        if (!rooms.contains(room))
        {
            rooms.add(room);
        }
        return this;
    }
}
