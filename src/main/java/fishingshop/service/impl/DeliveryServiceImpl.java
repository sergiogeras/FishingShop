package fishingshop.service.impl;

import fishingshop.dao.DeliveryDao;
import fishingshop.domain.order.Delivery;
import fishingshop.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Сергей on 10.11.2015.
 */

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryDao deliveryDao;

    @Override
    public void addDelivery(Delivery delivery) {
        deliveryDao.addDelivery(delivery);
    }

    @Override
    public void deleteDelivery(Integer id) {
        deliveryDao.deleteDelivery(id);
    }

    @Override
    public void editDelivery(Delivery delivery) {
        deliveryDao.editDelivery(delivery);
    }

    @Override
    public Delivery getDeliveryById(Integer id) {
        return deliveryDao.getDeliveryById(id);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryDao.getAllDeliveries();
    }
}
