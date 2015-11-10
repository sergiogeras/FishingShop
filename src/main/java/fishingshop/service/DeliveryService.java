package fishingshop.service;

import fishingshop.domain.order.Delivery;

import java.util.List;

/**
 * Created by Сергей on 10.11.2015.
 */
public interface DeliveryService {

    void addDelivery(Delivery delivery);
    void deleteDelivery(Integer id);
    void editDelivery(Delivery delivery);
    Delivery getDeliveryById(Integer id);
    List<Delivery> getAllDeliveries();
}
