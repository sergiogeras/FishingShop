package fishingshop.service.impl;

import fishingshop.dao.GoodsDao;
import fishingshop.domain.goods.Goods;
import fishingshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }

    @Override
    public void deleteGoods(Integer id) {
        goodsDao.deleteGoods(id);
    }

    @Override
    public void editGoods(Goods goods) {
        goodsDao.editGoods(goods);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    @Override
    public List<Goods> getGoodsByGroup(Integer id) {
        return goodsDao.getGoodsByGroup(id);
    }
}
