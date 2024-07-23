package used_pc_parts.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import used_pc_parts.backend.user.User;
import used_pc_parts.backend.user.UserRepository;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
  @Autowired UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    User admin = new User("admin@email.com");
    admin.setFirstName("admin");
    admin.setLastName("admin");
    // TODO Change this password for prod
    admin.setHashedPassword(encoder.encode("password"));
    admin.setRole("ADMIN");
    userRepository.save(admin);
  }
}
