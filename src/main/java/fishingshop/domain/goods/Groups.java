package fishingshop.domain.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "groups")
public class Groups implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Goods> goodsList;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    private Groups parentId;

    @OneToMany(mappedBy = "parentId",  fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Groups> childrenList;

    public Groups(String name, String description, String type) {
        this.name = name;
        this.description=description;
        this.type=type;
    }

    public Groups() {
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

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goods) {
        this.goodsList = goods;
    }

    public Groups getParentId() {
        return parentId;
    }

    public void setParentId(Groups parentId) {
        this.parentId = parentId;
    }

    public List<Groups> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Groups> childrenList) {
        this.childrenList = childrenList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
