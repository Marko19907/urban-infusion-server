package no.ntnu.webdev.webproject7.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CommentRepository extends CrudRepository<Comment, String> {
    public Optional<Comment> findById(String id);
}
