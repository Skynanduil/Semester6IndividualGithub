package walking.skeleton.serviceproducer.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import walking.skeleton.serviceproducer.models.Message;

import java.util.UUID;

@Repository
public interface MessageDAL extends CrudRepository<Message, UUID> {
}
