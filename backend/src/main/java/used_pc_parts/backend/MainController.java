package used_pc_parts.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/** The main controller for the backend. */
@Controller
public class MainController {
  @Autowired private SaleRepository saleRepository;

  @RequestMapping(path = "/greeting")
  public @ResponseBody String greeting() {
    return "Hello world!";
  }

  @GetMapping(path = "/sale/all")
  public @ResponseBody Iterable<Sale> getAllSales() {
    return saleRepository.findAll();
  }
}
