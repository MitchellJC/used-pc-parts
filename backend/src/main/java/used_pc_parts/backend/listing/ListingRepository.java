package used_pc_parts.backend.listing;

import org.springframework.data.repository.CrudRepository;
import used_pc_parts.backend.PCPart;

import java.util.List;

public interface ListingRepository extends CrudRepository<PCPartListing, Long> {
  List<PCPartListing> findByPcPartInOrderByCreationDateDesc(List<PCPart> parts);
}
