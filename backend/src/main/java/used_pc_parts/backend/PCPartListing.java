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

  private String sellerId;
  private String name;
  private String description;
  private String images;
  private PCPartCondition condition;

  private int quantity;
  private float price;

  protected PCPartListing() {}

  public PCPartListing(
      String sellerId,
      String name,
      String description,
      String images,
      PCPartCondition condition,
      int quantity,
      float price) {
    this.sellerId = sellerId;
    this.name = name;
    this.description = description;
    this.images = images;
    this.condition = condition;
    this.quantity = quantity;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public String getSellerId() {
    return sellerId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getImages() {
    return images;
  }

  public PCPartCondition getCondition() {
    return condition;
  }

  public int getQuantity() {
    return quantity;
  }

  public float getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setImages(String images) {
    this.images = images;
  }

  public void setCondition(PCPartCondition condition) {
    this.condition = condition;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
