package si.mougli.geekseek.domain.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import si.mougli.geekseek.domain.core.Repository;
import si.mougli.geekseek.domain.core.model.Identifiable;

public abstract class PersistenceRepository<T extends Identifiable> implements Repository<T>
{
    @PersistenceContext
    private EntityManager manager;

    private final Class<T> type;

    public PersistenceRepository(Class<T> type)
    {
        this.type = type;
    }

    @Override
    public Class<T> getType()
    {
        return type;
    }

    @Override
    public T store(T entity)
    {
        T merged = merge(entity);
        manager.persist(merged);
        return merged;
    }

    @Override
    public T get(String id)
    {
        return manager.find(type, id);
    }

    @Override
    public void remove(T entity)
    {
        manager.remove(merge(entity));
    }

    private T merge(T entity)
    {
        return manager.merge(entity);
    }

    protected EntityManager getManager()
    {
        return manager;
    }
}
