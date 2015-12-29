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
    List<Goods> getAllGoodsInStock();
    List<Goods> getGoodsByGroup(Integer id);
    List<Goods> getGoodsInStockByGroup(Integer id);
    void changeGoodsAmount(Goods goods);
    List<Goods> getGoodsViaMainSearch(String searchStr);
    List<Goods> getGoodsByCriteria(String name, String article, int priceFrom, int priceTo, int amountFrom, int amountTo);
}
