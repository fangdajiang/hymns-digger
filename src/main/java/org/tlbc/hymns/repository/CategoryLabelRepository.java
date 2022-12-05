package org.tlbc.hymns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.hymns.model.CategoryLabel;

@Repository
public interface CategoryLabelRepository extends JpaRepository<CategoryLabel, Integer> {
}
