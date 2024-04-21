package com.github.sawafrolov.loanrequestfilter.loanrequestservice.repositories

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import com.github.sawafrolov.loanrequestfilter.loanrequestservice.projections.LoanRequestInfo
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.UUID

@RepositoryRestResource(collectionResourceRel = "loan-requests", path = "loan-requests", excerptProjection = LoanRequestInfo::class)
interface LoanRequestRepository:
    PagingAndSortingRepository<LoanRequest, UUID>,
    CrudRepository<LoanRequest, UUID>,
    QuerydslPredicateExecutor<LoanRequest>
