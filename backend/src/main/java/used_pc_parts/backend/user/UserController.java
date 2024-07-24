package used_pc_parts.backend.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "user")
public class UserController {
  @Autowired private UserRepository userRepository;

  /**
   * @return JSON or XML of all user data.
   */
  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * @return View name for registration page.
   */
  @GetMapping(path = "/register")
  public String register() {
    return "register";
  }

  /**
   * Adds user with the given details to the repository.
   *
   * @param firstName The first name of the user.
   * @param lastName The last name of the user.
   * @param email The email of the user.
   * @return Success message.
   */
  @PostMapping(path = "/register")
  public @ResponseBody String register(
      @RequestParam String firstName,
      @RequestParam String lastName,
      @RequestParam String email,
      @RequestParam String password) {
    User user = new User();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setHashedPassword(encoder.encode(password));
    userRepository.save(user);
    return "Registration was successful.";
  }

  @GetMapping(path = "/remove")
  public @ResponseBody String removeUser(@RequestParam Long id) {
    userRepository.deleteById(id);
    return "Success";
  }

  @GetMapping(path = "/getFirstName")
  public @ResponseBody String getFirstName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Optional<User> user = userRepository.findByEmail(authentication.getName());
    if (user.isEmpty()) {
      return "User not found";
    }
    return user.get().getFirstName();
  }
}
