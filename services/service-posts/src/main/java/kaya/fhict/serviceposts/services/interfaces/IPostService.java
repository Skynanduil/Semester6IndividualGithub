package kaya.fhict.serviceposts.services.interfaces;

import kaya.fhict.serviceposts.dto.PostDTO;
import kaya.fhict.serviceposts.exceptions.PostNotFoundException;
import kaya.fhict.serviceposts.models.Post;

import java.util.UUID;

public interface IPostService {
    Post createPost(PostDTO post);
    Post getPostById(UUID id) throws PostNotFoundException;
    Post editPostById(UUID id, PostDTO post) throws PostNotFoundException;
    void deletePostById(UUID id) throws PostNotFoundException;
    Post[] getPostsByUserId(UUID id);
}
