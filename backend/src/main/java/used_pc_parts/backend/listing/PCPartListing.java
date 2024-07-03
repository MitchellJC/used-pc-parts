package used_pc_parts.backend.listing;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import used_pc_parts.backend.pc_part.PCPart;
import used_pc_parts.backend.sale.Sale;
import used_pc_parts.backend.user.User;

import java.util.Collection;
import java.util.Date;

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

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation_date")
  private Date creationDate;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_update_date")
  private Date lastUpdateDate;

  private int quantity;
  private float price;

  @OneToMany(mappedBy = "listing")
  private Collection<Sale> sales;

  protected PCPartListing() {}

  public PCPartListing(
      PCPart pcPart,
      User seller,
      Date creationDate,
      Date lastUpdateDate,
      int quantity,
      float price) {
    this.pcPart = pcPart;
    this.seller = seller;
    this.creationDate = creationDate;
    this.lastUpdateDate = lastUpdateDate;
    this.quantity = quantity;
    this.price = price;
  }

  //  Getters
  public long getId() {
    return id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

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
