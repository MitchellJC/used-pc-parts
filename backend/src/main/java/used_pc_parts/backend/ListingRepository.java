package used_pc_parts.backend;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingRepository extends CrudRepository<PCPartListing, Long> {
  List<PCPartListing> findByPcPartInOrderByCreationDateDesc(List<PCPart> parts);
}
