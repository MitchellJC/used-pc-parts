package used_pc_parts.backend;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import used_pc_parts.backend.listing.PCPartListing;
import used_pc_parts.backend.user.User;

import java.util.Date;

@Entity
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne private PCPartListing listing;

  @ManyToOne private User buyer;

  private int quantityPurchased;
  private float purchasePrice;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation_date")
  private Date creationDate;

  protected Sale() {}

  public Sale(PCPartListing listing, User buyer, int quantityPurchased, float purchasePrice) {
    this.listing = listing;
    this.buyer = buyer;
    this.quantityPurchased = quantityPurchased;
    this.purchasePrice = purchasePrice;
  }

  public long getId() {
    return id;
  }

  public PCPartListing getListing() {
    return listing;
  }

  public User getBuyer() {
    return buyer;
  }

  public int getQuantityPurchased() {
    return quantityPurchased;
  }

  public float getPurchasePrice() {
    return purchasePrice;
  }

  public Date getCreationDate() {
    return creationDate;
  }
}
