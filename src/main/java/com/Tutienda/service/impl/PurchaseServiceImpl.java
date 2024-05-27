package com.Tutienda.service.impl;

import com.Tutienda.entity.Item;
import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.ShoeStock;
import com.Tutienda.entity.product.Shoe;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IItemRepository;
import com.Tutienda.repository.IPurchaseRepository;
import com.Tutienda.repository.ShoeStockRepository;
import com.Tutienda.service.IPurchaseService;
import com.Tutienda.service.IShoeService;
import com.Tutienda.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
    private final IUserService userService;
    private final IPurchaseRepository purchaseRepository;
    private final IItemRepository itemRepository;
    private final IShoeService shoeService;
    private final ShoeStockRepository shoeStockRepository;

    public PurchaseServiceImpl(IItemRepository itemRepository, IUserService userService, IPurchaseRepository purchaseRepository, IShoeService shoeService, ShoeStockRepository shoeStockRepository) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.purchaseRepository = purchaseRepository;
        this.shoeService = shoeService;
        this.shoeStockRepository = shoeStockRepository;
    }

    @Override
    @Transactional
    public void savePurchase(Purchase purchase, String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            purchase.setUser(user.get());
            purchase.setDate(purchase.getDate());
            purchase.setTotalPurchase(purchase.getTotalPurchase());
            Purchase savedPurchase = purchaseRepository.save(purchase);
            for (Item item : savedPurchase.getItems()) {
                Optional<Shoe> optionalShoe = shoeService.findById(item.getShoe().getId());
                if (optionalShoe.isPresent()) {
                    Shoe shoe = optionalShoe.get();

                    for (ShoeStock stock : shoe.getShoeStocks()) {
                        if (stock.getSize().getShoeSize().getSizeAsInt() == item.getSize()) { // Verificar la talla
                            int newStock = stock.getStock() - item.getQuantity();
                            stock.setStock(Math.max(newStock, 0)); // Asegurar que el stock no sea negativo
                            shoeStockRepository.save(stock);
                        }
                    }
                    item.setPurchase(savedPurchase);
                    itemRepository.save(item);
                } else {
                    throw new RuntimeException("Shoe with ID " + item.getShoe().getId() + " not found");
                }
            }
        } else {
            throw new RuntimeException("User with username " + username + " not found");
        }
    }

    @Override
    public List<Purchase> getPurchaseByUser(User user) {
        return purchaseRepository.findAllByUser(user);
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

}
