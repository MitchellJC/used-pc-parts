package used_pc_parts.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
  @Autowired private UserRepository userRepository;

  @RequestMapping(path = "/greeting")
  public @ResponseBody String greeting() {
    return "Hello world!";
  }

  @PostMapping(path = "/add")
  public @ResponseBody String addNewUser(
      @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    userRepository.save(user);
    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
