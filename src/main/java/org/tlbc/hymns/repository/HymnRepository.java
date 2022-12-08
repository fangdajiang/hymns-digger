package org.tlbc.hymns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tlbc.hymns.model.HymnEntity;

@Repository
public interface HymnRepository extends JpaRepository<HymnEntity, Integer> {
}
