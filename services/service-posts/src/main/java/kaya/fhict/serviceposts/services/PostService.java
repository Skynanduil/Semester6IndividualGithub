package kaya.fhict.serviceposts.services;

import kaya.fhict.serviceposts.dal.interfaces.IPostDAL;
import kaya.fhict.serviceposts.dto.PostDTO;
import kaya.fhict.serviceposts.exceptions.PostNotFoundException;
import kaya.fhict.serviceposts.models.Post;
import kaya.fhict.serviceposts.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Qualifier("postService")
public class PostService implements IPostService {
    private final IPostDAL postDAL;

    @Autowired
    public PostService(IPostDAL postDAL){
        this.postDAL = postDAL;
    }

    @Override
    public Post createPost(PostDTO post) {
        Post postToDatabase = new Post(post.getPublisher());
        postToDatabase.setText(post.getText());
        postToDatabase.setImage(post.getImage());
        postToDatabase.setMarkdown(post.getMarkdown());
        return postDAL.save(postToDatabase);
    }

    @Override
    public Post getPostById(UUID id) throws PostNotFoundException {
        return postDAL.findById(id).orElseThrow(() -> new PostNotFoundException(id));
    }

    @Override
    public Post editPostById(UUID id, PostDTO post) throws PostNotFoundException {
        Post postInDB = getPostById(id);

        String text = post.getText();
        String image = post.getImage();
        String md = post.getMarkdown();

        if(!text.isBlank()) postInDB.setText(post.getText());
        if(!image.isBlank()) postInDB.setImage((post.getImage()));
        if(!md.isBlank()) postInDB.setMarkdown(post.getMarkdown());

        postDAL.save(postInDB);

        return postInDB;
    }

    @Override
    public void deletePostById(UUID id) throws PostNotFoundException {
        Post post = getPostById(id);
        postDAL.delete(post);
    }

    @Override
    public Post[] getPostsByUserId(UUID id) {
        return postDAL.findAllByPublisher(id);
    }
}
