package com.github.sawafrolov.loanrequestfilter.commons.documents

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.math.BigDecimal

@Document(indexName = "loan-requests")
class LoanRequestDocument(

    @Id
    val loanRequestId: String?,

    @Field(type = FieldType.Double, name = "amount")
    val amount: BigDecimal,

    @Field(type = FieldType.Integer, name = "term")
    val term: Int,

    @Field(type = FieldType.Text, name = "title")
    val title: String,

    @Field(type = FieldType.Text, name = "fio")
    val fio: String?,

    @Field(type = FieldType.Text, name = "company_name")
    val companyName: String?,

    @Field(type = FieldType.Text, name = "description")
    val description: String?,

    @Field(type = FieldType.Text, name = "inn")
    val inn: String,

    @Field(type = FieldType.Integer, name = "region_number")
    val regionNumber: Int,

    @Field(type = FieldType.Double, name = "capital")
    val capital: BigDecimal
)
