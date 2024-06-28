package used_pc_parts.backend;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    String salt = generateSalt();
    String hashedPassword = encoder.encode(salt + password);

    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setSalt(salt);
    user.setHashedPassword(hashedPassword);
    userRepository.save(user);
    return "Saved";
  }

  private String generateSalt() {
    String salt;
    SecureRandom random = new SecureRandom();
    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    final int saltNumBytes = 10;
    byte[] saltBytes = new byte[saltNumBytes];

    random.nextBytes(saltBytes);
    salt = encoder.encodeToString(saltBytes);
    return salt;
  }
}
