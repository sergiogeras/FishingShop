package fishingshop.service;

import fishingshop.domain.order.Status;

import java.util.List;

/**
 * Created by ������ on 17.11.2015.
 */
public interface StatusService {
    void addStatus(Status status);
    void deleteStatus(Integer id);
    void editStatus(Status status);
    Status getStatusById(Integer id);
    List<Status> getAllStatuses();
}
