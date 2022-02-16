package no.ntnu.webdev.webproject7.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment getCommentById(String id) {
        // Guard condition
        if (id == null) {
            return null;
        }
        Optional<Comment> result = this.commentRepository.findById(id);
        return result.orElse(null);
    }

    public boolean addComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        this.commentRepository.save(comment);
        return this.getCommentById(comment.getId()) != null;
    }

    public List<Comment> getAllComments() {
        return StreamSupport
                .stream(this.commentRepository.findAll().spliterator(), false)
                .toList();
    }

    public boolean updateComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        this.commentRepository.save(comment);
        return true;
    }

    public boolean deleteComment(String id) {
        if (id == null) {
            return false;
        }
        this.commentRepository.deleteById(id);
        return this.getCommentById(id) == null;
    }
}
