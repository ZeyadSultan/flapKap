package task.flapKap.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import task.flapKap.dto.DepositRequest;
import task.flapKap.dto.ProductDto;
import task.flapKap.dto.PurchaseResponse;
import task.flapKap.exception.ApiError;
import task.flapKap.model.Product;
import task.flapKap.model.User;
import task.flapKap.service.ProductService;
import task.flapKap.service.UserService;
import task.flapKap.service.VendingMachineService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service

public class VendingMachineServiceImpl implements VendingMachineService {

    private final UserService userService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public VendingMachineServiceImpl(UserService userService, ProductService productService, ModelMapper modelMapper) {
        this.userService = userService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void depositCoins( User user, DepositRequest depositRequest) {
        validateDepositCoins(depositRequest.getCoins());
        long totalDeposit = calculateTotalDeposit(depositRequest.getCoins());
        user.setDeposit(user.getDeposit() + totalDeposit);

        userService.save(user);
    }

    @Override
    public PurchaseResponse buyProducts(Long productId, int amount, User user) {
        Product product = productService.getById(productId);

        if(product == null) {
            throw ApiError.notFound("product not found!");
        }
        long totalCost = product.getCost() * amount;
        if(user.getDeposit() < totalCost) {
            throw ApiError.badRequest("Insufficient funds");
        }

        if(product.getAmountAvailable() < amount) {
            throw ApiError.notFound("Not available amount found!");
        }

        user.setDeposit(user.getDeposit() - totalCost);
        userService.save(user);

        int remainingAmount = product.getAmountAvailable() - amount;
        product.setAmountAvailable(remainingAmount);
        productService.save(product);

        long change = user.getDeposit();
        Map<String, Long> changeDetails = calculateChange(change);

        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setSellerId(user.getId());
        return new PurchaseResponse(totalCost, productDto, amount, changeDetails);

    }

    @Override
    public void resetDeposit(User user) {
        user.setDeposit(0L);
        userService.save(user);
    }

    public void validateDepositCoins(Map<String, Long> coins) {
        Set<String> allowedCoins = Set.of("5", "10", "20", "50", "100");

        for (String coin : coins.keySet()) {
            if (!allowedCoins.contains(coin)) {
                throw ApiError.badRequest("Invalid coin type: " + coin);
            }
        }
    }
    private long calculateTotalDeposit(Map<String, Long> coins) {
        return coins.entrySet().stream()
                .mapToLong(entry -> Long.parseLong(entry.getKey()) * entry.getValue())
                .sum();
    }

    private Map<String, Long> calculateChange(long amount) {
        Map<String, Long> changeDetails = new LinkedHashMap<>();

        long[] coins = {100, 50, 20, 10, 5};

        for (long coin : coins) {
            long count = amount / coin;
            if (count > 0) {
                changeDetails.put(coin + "-cent", count);
                amount %= coin;
            }
        }

        return changeDetails;
    }
}
