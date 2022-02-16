package no.ntnu.webdev.webproject7.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CommentRepository extends CrudRepository<Comment, String> {
}
