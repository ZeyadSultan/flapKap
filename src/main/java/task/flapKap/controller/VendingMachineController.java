package task.flapKap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import task.flapKap.dto.DepositRequest;
import task.flapKap.dto.PurchaseResponse;
import task.flapKap.model.User;
import task.flapKap.service.VendingMachineService;
import task.flapKap.util.AuthenticationUser;

@RestController
@RequestMapping("/api/vendingMachine")
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<String> deposit(@PathVariable("id") Long userId,
                                          @RequestBody DepositRequest depositRequest) {
        vendingMachineService.depositCoins(userId, depositRequest);
        return ResponseEntity.ok("Coins deposited successfully!");
    }

    @PostMapping("/buy")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<PurchaseResponse> buyProduct( @RequestParam Long productId,
            @RequestParam int amount, Authentication authentication
    ) {
        User user = AuthenticationUser.get(authentication);
        return ResponseEntity.ok(vendingMachineService.buyProducts(productId,amount, user));
    }

    @PostMapping("/reset")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<String> resetDeposit(Authentication authentication) {
        User user = AuthenticationUser.get(authentication);
        vendingMachineService.resetDeposit(user);
        return ResponseEntity.ok("User's deposit resetted to zero");
    }
}
