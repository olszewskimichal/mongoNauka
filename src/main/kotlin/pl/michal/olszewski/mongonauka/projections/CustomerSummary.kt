package pl.michal.olszewski.mongonauka.projections

import org.springframework.beans.factory.annotation.Value

internal interface CustomerSummary {

    @get:Value("#{target.firstname + ' ' + target.lastname}")
    val fullName: String
}