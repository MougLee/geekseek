package si.mougli.geekseek.domain.core;

import si.mougli.geekseek.domain.core.model.Identifiable;

public interface Repository<T extends Identifiable>
{
    Class<T> getType();

    T store(T entity);

    T get(String id);

    void remove(T entity);
}
