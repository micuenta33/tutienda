package com.Tutienda.service;

import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;

import java.util.List;

public interface IPurchaseService {
    boolean savePurchase(Purchase purchase, String username);

    List<Purchase> getPurchaseByUser(User user);

    Purchase getPurchaseById(Long id);
}
