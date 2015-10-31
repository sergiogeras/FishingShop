package fishingshop.service;


import fishingshop.domain.goods.Goods;

import java.util.List;

public interface GoodsService {
    void addGoods(Goods goods);
    void deleteGoods(Integer id);
    void editGoods(Goods goods);
    Goods getGoodsById(Integer id);
    List<Goods> getAllGoods();
    List<Goods> getGoodsByGroup(Integer id);
    void changeGoodsAmount(Goods goods);
}
