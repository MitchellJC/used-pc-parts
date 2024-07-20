package used_pc_parts.backend.listing;

import org.springframework.data.repository.CrudRepository;
import used_pc_parts.backend.pc_part.PCPart;
import used_pc_parts.backend.user.User;

import java.util.List;

public interface ListingRepository extends CrudRepository<PCPartListing, Long> {
  List<PCPartListing> findAllBySeller(User seller);
  List<PCPartListing> findAllByOrderByCreationDateDesc();
  List<PCPartListing> findAllByQuantityGreaterThanOrderByCreationDateDesc(int quantity);
  List<PCPartListing> findByPcPartInOrderByCreationDateDesc(List<PCPart> parts);
}
