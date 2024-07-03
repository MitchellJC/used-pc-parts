package used_pc_parts.backend;

import java.util.List;
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
  @Autowired private PCPartRepository partRepository;
  @Autowired private SaleRepository saleRepository;

  /**
   * @return JSON or XML of all listing data.
   */
  @GetMapping(path = "/all")
  public @ResponseBody Iterable<PCPartListing> getAllListings() {
    return listingRepository.findAll();
  }

  /**
   * @param category category of interest.
   * @return All listings from category of interest.
   */
  @GetMapping(path = "/all/category")
  public @ResponseBody Iterable<PCPartListing> allFromCategory(@RequestParam String category) {
    List<PCPart> categoryParts = partRepository.findByCategory(PCPartCategory.valueOf(category));
    return listingRepository.findByPcPartInOrderByCreationDateDesc(categoryParts);
  }

  /**
   * @return View for creating a listing.
   */
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
      @RequestParam float price)
      throws ResponseStatusException {
    PCPart pcPart = new PCPart();
    PCPartListing listing = new PCPartListing();
    PCPartCondition condition = PCPartCondition.valueOf(conditionString);
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Optional<User> seller = userRepository.findByEmail(authentication.getName());

    if (seller.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
    }

    // Create PCPart object
    pcPart.setName(name);
    pcPart.setDescription(description);
    pcPart.setImages(images);
    pcPart.setPartCondition(condition);
    pcPart.setCategory(PCPartCategory.valueOf(categoryString));
    partRepository.save(pcPart);

    // Create listing object
    listing.setSeller(seller.get());
    listing.setQuantity(quantity);
    listing.setPrice(price);
    listing.setPcPart(pcPart);
    listingRepository.save(listing);
    return "Saved";
  }

  /**
   * @return View for buy page.
   */
  @GetMapping(path = "/buy")
  public String buyListing() {
    return "buy-listing";
  }

  /**
   * Handles buying a listing.
   *
   * @param listingId id of the listing to buy.
   * @param quantity the quantity of the part in listing to buy.
   * @return success message.
   * @throws ResponseStatusException if listing does not exist or quantity is not available.
   */
  @PostMapping(path = "/buy")
  public @ResponseBody String buyListing(@RequestParam Long listingId, @RequestParam int quantity)
      throws ResponseStatusException {
    PCPartListing listing;
    Sale sale;
    Optional<PCPartListing> optionalListing = listingRepository.findById(listingId);
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Optional<User> seller = userRepository.findByEmail(authentication.getName());

    // Check seller exists
    if (seller.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
    }

    // Check listing exists
    if (optionalListing.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Listing does not exist.");
    }
    listing = optionalListing.get();

    // Check quantity is available
    if (listing.getQuantity() < quantity) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Quantity larger than available quantity.");
    }

    // Create sale
    sale = new Sale(listing, seller.get(), quantity, listing.getPrice());
    saleRepository.save(sale);

    listing.setQuantity(listing.getQuantity() - quantity);
    listingRepository.save(listing);
    return "Success";
  }
}
