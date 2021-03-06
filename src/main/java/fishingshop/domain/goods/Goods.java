package fishingshop.domain.goods;


import fishingshop.domain.order.Orders;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods implements Serializable, Comparable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String article;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer price;

    @Column
    private String manufacturer;

    @Column(name = "GOODS_AMOUNT")
    private Integer goodsAmount;    // Remains goods in the store

    @Column
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    private Groups groups;

    @OneToMany
    private List<Orders> orders;

    public Goods() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer balance) {
        this.goodsAmount = balance;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Goods)o).name);
    }
}
