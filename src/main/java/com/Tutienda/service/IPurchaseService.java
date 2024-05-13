package com.Tutienda.service;

import com.Tutienda.entity.Purchase;

public interface IPurchaseService {
    void savePurchase(Purchase purchase, String username);
}
