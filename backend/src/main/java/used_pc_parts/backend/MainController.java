package used_pc_parts.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
