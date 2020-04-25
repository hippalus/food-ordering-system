package com.hippalus.sharedkernel.infrastructure.jpa;

import com.hippalus.sharedkernel.domain.AbstractAggregateRoot;
import com.hippalus.sharedkernel.domain.DomainObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IJpaRepository<T extends AbstractAggregateRoot<I>, I extends DomainObjectId> extends JpaRepository<T,I>, JpaSpecificationExecutor<T> {

}