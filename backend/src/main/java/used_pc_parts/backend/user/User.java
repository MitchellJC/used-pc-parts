package used_pc_parts.backend.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import used_pc_parts.backend.listing.PCPartListing;
import used_pc_parts.backend.sale.Sale;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  private String email;

  private String firstName;
  private String lastName;
  private String hashedPassword;

  @OneToMany(mappedBy = "seller")
  private List<PCPartListing> listings;

  @OneToMany(mappedBy = "buyer")
  @JsonIgnore
  private List<Sale> purchases;

  protected User() {}

  public User(String email, String firstName, String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Getters
  public Long getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public List<Sale> getPurchases() {
    return purchases;
  }

  // Setters
  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }
}
