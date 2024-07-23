package used_pc_parts.backend;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import used_pc_parts.backend.user.User;
import used_pc_parts.backend.user.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Find user
    Optional<User> user = userRepository.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Username not found.");
    }

    // Construct and return user details.
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.get().getEmail())
        .password(user.get().getHashedPassword())
        .authorities(user.get().getRole())
        .build();
  }
}
