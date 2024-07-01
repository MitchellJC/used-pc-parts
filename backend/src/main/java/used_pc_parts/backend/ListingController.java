package used_pc_parts.backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping(path = "/listing")
public class ListingController {
  @Autowired private UserRepository userRepository;
  @Autowired private ListingRepository listingRepository;

  /**
   * @return JSON or XML of all listing data.
   */
  @GetMapping(path = "/all")
  public @ResponseBody Iterable<PCPartListing> getAllListings() {
    return listingRepository.findAll();
  }

  @GetMapping(path = "/create")
  public String getCreateListingView() {
    return "create-listing";
  }

  /**
   * Add listing with given details to the repository.
   *
   * @param name Name of the part
   * @param description Description of the part
   * @param images List of image locations
   * @param categoryString Category of the part
   * @param conditionString Condition of the part
   * @param quantity Number of this part for sale
   * @param price Price of this part
   * @return Success message
   */
  @PostMapping(path = "/create")
  public @ResponseBody String addNewListing(
      @RequestParam String name,
      @RequestParam String description,
      @RequestParam String images,
      @RequestParam String categoryString,
      @RequestParam String conditionString,
      @RequestParam int quantity,
      @RequestParam float price) {
    PCPartListing listing = new PCPartListing();
    PCPartCondition condition = PCPartCondition.valueOf(conditionString);
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Optional<User> seller = userRepository.findByEmail(authentication.getName());

    if (seller.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
    }

    listing.setSeller(seller.get());
    listing.setName(name);
    listing.setDescription(description);
    listing.setImages(images);
    listing.setPartCondition(condition);
    listing.setQuantity(quantity);
    listing.setPrice(price);
    listingRepository.save(listing);
    return "Saved";
  }
}