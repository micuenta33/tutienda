package com.Tutienda.service.impl;

import com.Tutienda.entity.Item;
import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IItemRepository;
import com.Tutienda.repository.IPurchaseRepository;
import com.Tutienda.service.IPurchaseService;
import com.Tutienda.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
    private final IUserService userService;
    private final IPurchaseRepository purchaseRepository;
    private final IItemRepository itemRepository;

    public PurchaseServiceImpl(IItemRepository itemRepository, IUserService userService, IPurchaseRepository purchaseRepository) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void savePurchase(Purchase purchase, String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            purchase.setUser(user.get());
            purchase.setDate(purchase.getDate());
            purchase.setTotalPurchase(purchase.getTotalPurchase());
            Purchase savedPurchase =purchaseRepository.save(purchase);
            for (Item item : savedPurchase.getItems()) {
                item.setPurchase(savedPurchase);
                itemRepository.save(item);
            }
        }

    }
}
