package used_pc_parts.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** Represents a PCPart that can be bought and sold. */
@Entity
public class PCPartListing {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String description;

  protected PCPart() {}

  public PCPart(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
