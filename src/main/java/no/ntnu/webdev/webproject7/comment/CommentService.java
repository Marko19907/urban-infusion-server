package no.ntnu.webdev.webproject7.comment;

import no.ntnu.webdev.webproject7.comment.Comment;
import org.apache.tomcat.jni.Local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class CommentService {
    private final List<Comment> comments;

    public CommentService() {
        this.comments = new ArrayList<>(
                Arrays.asList(
                        new Comment(0, 0, 0, "Superb!", LocalDate.now()),
                        new Comment(1, 0, 1, "Tasted okay", LocalDate.of(2022, 02, 01),
                        new Comment(2, 1, 2, "I recommend!", LocalDate.of(2021, 8, 15))
                ));
    }

    public boolean addComment(Comment comment) {
        if (this.comments.contains(comment)) {
            return false;
        }
        return this.comments.add(comment);
    }

    public List<Comment> getAllComments() {
        return this.comments;
    }

    public boolean updateComment(Comment comment) {
        if (!this.comments.contains(comment)) {
            return false;
        }
        this.comments.set(this.comments.indexOf(comment), comment);
        return true;
    }

    public boolean deleteComment(String id) {
        return this.comments.removeIf(comment -> Objects.equals(comment.getId(), id));
    }
}
