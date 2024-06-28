package used_pc_parts.backend;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

/** The main controller for the backend. */
@Controller
public class MainController {
  @Autowired private UserRepository userRepository;
  @Autowired private ListingRepository listingRepository;

  @RequestMapping(path = "/greeting")
  public @ResponseBody String greeting() {
    return "Hello world!";
  }

  /**
   * @return JSON or XML of all listing data.
   */
  @GetMapping(path = "/allListings")
  public @ResponseBody Iterable<PCPartListing> getAllListings() {
    return listingRepository.findAll();
  }

  /**
   * Add listing with given details to the repository.
   *
   * @param sellerId User id of the seller
   * @param name Name of the part
   * @param description Description of the part
   * @param images List of image locations
   * @param conditionString Condition of the part
   * @param quantity Number of this part for sale
   * @param price Price of this part
   * @return Success message
   */
  @PostMapping(path = "/addListing")
  public @ResponseBody String addNewListing(
      @RequestParam Long sellerId,
      @RequestParam String name,
      @RequestParam String description,
      @RequestParam String images,
      @RequestParam String conditionString,
      @RequestParam int quantity,
      @RequestParam float price) {
    PCPartListing listing = new PCPartListing();
    Optional<User> seller = userRepository.findById(sellerId);
    PCPartCondition condition = PCPartCondition.valueOf(conditionString);

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
