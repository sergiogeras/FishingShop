package fishingshop.dao;

import fishingshop.domain.order.Payment;

import java.util.List;

/**
 * Created by Сергей on 10.11.2015.
 */
public interface PaymentDao {
    void addPayment(Payment payment);
    void deletePayment(Integer id);
    void editPayment(Payment payment);
    Payment getPaymentById(Integer id);
    List<Payment> getAllPayments();
}
