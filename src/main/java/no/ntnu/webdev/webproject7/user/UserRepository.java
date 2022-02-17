package no.ntnu.webdev.webproject7.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends CrudRepository<User, String> {
}
