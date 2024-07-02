package used_pc_parts.backend;

import jakarta.persistence.*;

@Entity
public class PCPartListing {
  @Id private long id;

  @MapsId
  @OneToOne
  @JoinColumn(name = "id")
  private PCPart pcPart;

  @ManyToOne
  @JoinColumn(name = "seller_id")
  private User seller;

  private int quantity;
  private float price;

  protected PCPartListing() {}

  public PCPartListing(PCPart pcPart, User seller, int quantity, float price) {
    this.pcPart = pcPart;
    this.seller = seller;
    this.quantity = quantity;
    this.price = price;
  }

  //  Getters
  public PCPart getPcPart() {
    return pcPart;
  }

  public User getSeller() {
    return seller;
  }

  public int getQuantity() {
    return quantity;
  }

  public float getPrice() {
    return price;
  }

  // Setters
  public void setPcPart(PCPart pcPart) {
    this.pcPart = pcPart;
  }

  public void setSeller(User seller) {
    this.seller = seller;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
