package used_pc_parts.backend.sale;

import org.springframework.data.repository.CrudRepository;
import used_pc_parts.backend.listing.PCPartListing;
import used_pc_parts.backend.user.User;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {
  List<Sale> findAllByListingIn(List<PCPartListing> listings);
}
