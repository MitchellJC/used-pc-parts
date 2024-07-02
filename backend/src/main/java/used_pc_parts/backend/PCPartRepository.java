package used_pc_parts.backend;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PCPartRepository extends CrudRepository<PCPart, Long> {

  Optional<PCPart> findById(long id);
}
