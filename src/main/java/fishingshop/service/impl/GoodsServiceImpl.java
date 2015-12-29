package fishingshop.service.impl;

import fishingshop.dao.GoodsDao;
import fishingshop.domain.goods.Goods;
import fishingshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Goods> getAllGoodsInStock() {
        return goodsDao.getAllGoodsInStock();
    }

    @Override
    public List<Goods> getGoodsByGroup(Integer id) {
        return goodsDao.getGoodsByGroup(id);
    }

    @Override
    public List<Goods> getGoodsInStockByGroup(Integer id) {
        return goodsDao.getGoodsInStockByGroup(id);
    }

    @Override
    public void changeGoodsAmount(Goods goods) {
        goodsDao.changeGoodsAmount(goods);
    }

    @Override
    public List<Goods> getGoodsViaMainSearch(String searchStr) {
        return goodsDao.getGoodsViaMainSearch(searchStr);
    }

    /** Create list of goodsAmount numbers */
    @Override
    public List<Integer> getAmountList(Goods goods){
        List<Integer> list=new ArrayList<>();
        if(goods.getGoodsAmount()!=0){
            for(int i=1; i<= goods.getGoodsAmount() && i<=5; i++){
                list.add(i);
            }
        } else list.add(0);

       return list;
    }

    @Override
    public List<Goods> getGoodsByCriteria(String name, String article, int priceFrom, int priceTo, int amountFrom, int amountTo) {

        return goodsDao.getGoodsByCriteria(name, article, priceFrom, priceTo, amountFrom, amountTo);
    }
}
