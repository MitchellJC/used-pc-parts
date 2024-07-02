package used_pc_parts.backend;

import jakarta.persistence.*;

/** Represents a PCPart that can be bought and sold. */
@Entity
public class PCPart {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;
  private String images;

  @Enumerated(EnumType.STRING)
  private PCPartCategory category;

  @Enumerated(EnumType.STRING)
  private PCPartCondition partCondition;

  @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)
  private PCPartListing listing;

  protected PCPart() {}

  public PCPart(
      String name, String description, PCPartCategory category, PCPartCondition partCondition) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.partCondition = partCondition;
  }

  public String getName() {
    return name;
  }

  // Getters
  public String getDescription() {
    return description;
  }

  public String getImages() {
    return images;
  }

  public PCPartCategory getCategory() {
    return category;
  }

  public PCPartCondition getPartCondition() {
    return partCondition;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public void setCategory(PCPartCategory category) {
    this.category = category;
  }

  public void setPartCondition(PCPartCondition partCondition) {
    this.partCondition = partCondition;
  }
}
