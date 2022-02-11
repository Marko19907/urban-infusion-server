package no.ntnu.webdev.webproject7.comment;

import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentService {
    private final List<Comment> comments;
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.comments = new ArrayList<>(
                Arrays.asList(
                        new Comment("0", "0", "0", "Superb!", LocalDate.now()),
                        new Comment("1", "0", "1", "Tasted okay", LocalDate.of(2022, 2, 1)),
                        new Comment("2", "1", "2", "I recommend!", LocalDate.of(2021, 8, 15))));
        this.commentRepository = commentRepository;
    }

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
                .collect(Collectors.toList());
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
