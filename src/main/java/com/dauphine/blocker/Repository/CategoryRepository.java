package com.dauphine.blocker.Repository;

import com.dauphine.blocker.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
