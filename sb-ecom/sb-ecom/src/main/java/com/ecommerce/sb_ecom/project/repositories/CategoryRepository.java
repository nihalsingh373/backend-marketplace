package com.ecommerce.sb_ecom.project.repositories;

import com.ecommerce.sb_ecom.project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(String categoryName);
}
