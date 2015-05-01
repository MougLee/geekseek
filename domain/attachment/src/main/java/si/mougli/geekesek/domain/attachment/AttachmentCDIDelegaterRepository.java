package si.mougli.geekesek.domain.attachment;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import si.mougli.geekesek.domain.attachment.model.Attachment;
import si.mougli.geekseek.domain.Repository;


@ApplicationScoped
public class AttachmentCDIDelegaterRepository implements Repository<Attachment> {

    @EJB
    private AttachmentRepository repo;

    @Override
    public Class<Attachment> getType() {
        return repo.getType();
    }

    @Override
    public Attachment store(Attachment entity) {
        return repo.store(entity);
    }

    @Override
    public Attachment get(String id) {
        return repo.get(id);
    }

    @Override
    public void remove(Attachment entity) {
        repo.remove(entity);
    }
}
