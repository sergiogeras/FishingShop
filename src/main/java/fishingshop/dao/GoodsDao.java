package fishingshop.dao;

import fishingshop.domain.goods.Goods;

import java.util.List;

/**
 * Created by Сергей on 02.08.2015.
 */
public interface GoodsDao {
    void addGoods(Goods goods);
    void deleteGoods(Integer id);
    void editGoods(Goods goods);
    Goods getGoodsById(Integer id);
    List<Goods> getAllGoods();
    List<Goods> getGoodsByGroup(Integer id);
}
