package com.Tutienda.repository;

import com.Tutienda.entity.users.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
