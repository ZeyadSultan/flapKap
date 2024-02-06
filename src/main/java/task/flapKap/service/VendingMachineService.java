package task.flapKap.service;

import org.springframework.stereotype.Service;
import task.flapKap.dto.DepositRequest;
import task.flapKap.dto.PurchaseResponse;
import task.flapKap.model.User;

import java.util.Map;

public interface VendingMachineService {

    public void depositCoins(User user, DepositRequest depositRequest);
    public PurchaseResponse buyProducts(Long productId, int amount, User user);
    public void resetDeposit(User user);
}
