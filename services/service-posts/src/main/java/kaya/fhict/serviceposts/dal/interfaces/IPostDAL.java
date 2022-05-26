package kaya.fhict.serviceposts.dal.interfaces;

import kaya.fhict.serviceposts.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPostDAL extends CrudRepository<Post, UUID> {
    Post[] findAllByPublisher(UUID id);
}
