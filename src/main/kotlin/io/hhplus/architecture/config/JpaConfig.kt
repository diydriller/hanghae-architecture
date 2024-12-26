package io.hhplus.architecture.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = ["io.hhplus.architecture.domain"])
@EnableJpaRepositories(basePackages = ["io.hhplus.architecture.infrastructure"])
class JpaConfig