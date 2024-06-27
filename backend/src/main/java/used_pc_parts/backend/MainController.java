package used_pc_parts.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** The main controller for the backend. */
@Controller
public class MainController {
  @Autowired private UserRepository userRepository;
  @Autowired private ListingRepository listingRepository;

  /**
   * Greeting request that returns simple test greeting response.
   *
   * @return Greeting message
   */
  @RequestMapping(path = "/greeting")
  public @ResponseBody String greeting() {
    return "Hello world!";
  }

  /**
   * Adds user with the given details to the repository.
   *
   * @param firstName The first name of the user.
   * @param lastName The last name of the user.
   * @param email The email of the user.
   * @return Success message.
   */
  @PostMapping(path = "/addUser")
  public @ResponseBody String addNewUser(
      @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    userRepository.save(user);
    return "Saved";
  }

  /**
   * @return JSON or XML of all user data.
   */
  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  @PostMapping(path = "/addListing")
  public @ResponseBody String addNewListing(
      @RequestParam String name,
      @RequestParam String description,
      @RequestParam String images,
      @RequestParam PCPartCondition condition,
      @RequestParam int quantity,
      @RequestParam float price) {
    PCPartListing listing = new PCPartListing();
    listing.setName(name);
    listing.setDescription(description);
    listing.setImages(images);
    listing.setCondition(condition);
    listing.setQuantity(quantity);
    listing.setPrice(price);
    listingRepository.save(listing);
    return "Saved";
  }
}
