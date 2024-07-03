package used_pc_parts.backend.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/sale")
public class SaleController {
  @Autowired private SaleRepository saleRepository;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Sale> getAllSales() {
    return saleRepository.findAll();
  }
}
