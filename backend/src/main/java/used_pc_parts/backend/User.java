package used_pc_parts.backend;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;
  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "seller")
  private List<PCPartListing> listings;

  protected User() {}

  public User(String email, String firstName, String lastName) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

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

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
