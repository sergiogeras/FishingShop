package fishingshop.service.impl;

import fishingshop.dao.StatusDao;
import fishingshop.domain.order.Status;
import fishingshop.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Сергей on 17.11.2015.
 */

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusDao statusDao;

    @Override
    public void addStatus(Status status) {
        statusDao.addStatus(status);
    }

    @Override
    public void deleteStatus(Integer id) {
        statusDao.deleteStatus(id);
    }

    @Override
    public void editStatus(Status status) {
        statusDao.editStatus(status);
    }

    @Override
    public Status getStatusById(Integer id) {
        return statusDao.getStatusById(id);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusDao.getAllStatuses();
    }
}
