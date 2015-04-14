package si.mougli.geekseek.domain;

import si.mougli.geekseek.domain.model.Identifiable;

public interface Repository<T extends Identifiable>
{
    Class<T> getType();

    T store(T entity);

    T get(String id);

    void remove(T entity);
}
