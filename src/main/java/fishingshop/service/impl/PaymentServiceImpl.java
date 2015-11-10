package fishingshop.service.impl;

import fishingshop.dao.PaymentDao;
import fishingshop.domain.order.Payment;
import fishingshop.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Сергей on 10.11.2015.
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public void addPayment(Payment payment) {
        paymentDao.addPayment(payment);
    }

    @Override
    public void deletePayment(Integer id) {
        paymentDao.deletePayment(id);
    }

    @Override
    public void editPayment(Payment payment) {
        paymentDao.editPayment(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }
}
