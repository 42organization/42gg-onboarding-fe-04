package jpabook.onboarding.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jpabook.onboarding.data.entity.Sugang;

@Repository
public interface SugangRepository extends JpaRepository<Sugang, Long> {
}
