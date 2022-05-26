package kaya.fhict.serviceposts.controllers;

import kaya.fhict.serviceposts.dto.PostDTO;
import kaya.fhict.serviceposts.exceptions.PostNotFoundException;
import kaya.fhict.serviceposts.services.interfaces.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(value= "/post")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(@Qualifier("postService") IPostService postService) {
        this.postService = postService;
    }


    @GetMapping("/test")
    public ResponseEntity<PostDTO> testMethod(){
        var post = new PostDTO();
        post.setId(UUID.randomUUID());
        post.setText("Hello World");
        post.setPublisher(UUID.randomUUID());

       // //Kafka test?
       // KafkaProducer prod = new KafkaProducer();
       // prod.send("Hello world");

        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post){
        return new ResponseEntity<PostDTO>(new PostDTO(postService.createPost(post)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable UUID id) throws PostNotFoundException {
        PostDTO post = new PostDTO(postService.getPostById(id));
        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> editPostById(@PathVariable UUID id, @RequestBody PostDTO post) throws PostNotFoundException {
        return new ResponseEntity<PostDTO>(new PostDTO(postService.editPostById(id, post)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable UUID id) throws PostNotFoundException {
        postService.deletePostById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //@GetMapping("/user/{id}")
    //public ResponseEntity<PostDTO[]> getPostsByUserId(@PathVariable UUID id){
    //    PostDTO[] posts = Arrays.stream(postService.getPostsByUserId(id)).map(post -> {
    //        return new PostDTO(post);
    //    });
    //    return new ResponseEntity<PostDTO[]>(posts, HttpStatus.OK);
    //}
}
