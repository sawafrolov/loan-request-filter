package com.github.sawafrolov.loanrequestfilter.starter.jpa.repositories

import com.github.sawafrolov.loanrequestfilter.commons.entities.LoanRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LoanRequestRepository: CrudRepository<LoanRequest, UUID>
