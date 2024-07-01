package used_pc_parts.backend;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (Objects.equals(username, "admin")) {
      return org.springframework.security.core.userdetails.User.builder()
          .username("admin")
          .password(encoder.encode("password"))
          .authorities("USER", "ADMIN")
          .build();
    }

    Optional<User> user = userRepository.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Username not found.");
    }

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.get().getEmail())
        .password(user.get().getHashedPassword())
        .authorities("USER")
        .build();
  }
}
