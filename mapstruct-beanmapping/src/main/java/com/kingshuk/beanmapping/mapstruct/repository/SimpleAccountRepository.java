package com.kingshuk.beanmapping.mapstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kingshuk.beanmapping.mapstruct.entities.SimpleAccountEntity;

@Repository
public interface SimpleAccountRepository extends JpaRepository<SimpleAccountEntity, Long> {

}
