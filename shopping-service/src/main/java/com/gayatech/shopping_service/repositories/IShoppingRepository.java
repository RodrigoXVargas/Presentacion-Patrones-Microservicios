package com.gayatech.shopping_service.repositories;

import com.gayatech.shopping_service.models.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingRepository extends JpaRepository<Shopping,Long> {
}
