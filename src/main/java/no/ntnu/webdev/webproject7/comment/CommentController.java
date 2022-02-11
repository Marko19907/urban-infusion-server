package no.ntnu.webdev.webproject7.comment;

import no.ntnu.webdev.webproject7.comment.Comment;
import no.ntnu.webdev.webproject7.comment.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("comments")

public class CommentController {
    private final CommentService commentService;


    public CommentController() {
        this.commentService = new CommentService();
    }

    @GetMapping("")
    public List<Comment> getAllComments() {
        return this.commentService.getAllComments();
    }

    @PostMapping("")
    public void addProduct(Comment comment) {
        this.commentService.addComment(comment);
    }

    @PutMapping("")
    public ResponseEntity<String> updateComment(Comment comment){
        return this.commentService.updateComment(comment)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id) {
        return this.commentService.deleteComment(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
