package used_pc_parts.backend.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import used_pc_parts.backend.user.User;
import used_pc_parts.backend.user.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/sale")
public class SaleController {
  @Autowired private SaleRepository saleRepository;
  @Autowired private UserRepository userRepository;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Sale> getAllSales() {
    return saleRepository.findAll();
  }

  @GetMapping(path = "/purchases")
  public @ResponseBody List<Sale> getMySales() {
    Collection<Sale> sales;
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    Optional<User> user = userRepository.findByEmail(authentication.getName());

    if (user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist.");
    }

    return user.get().getPurchases();
  }
}
