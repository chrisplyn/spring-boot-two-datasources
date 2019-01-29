package com.twoDB;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "teacherEntityManagerFactory",
    transactionManagerRef = "teacherTransactionManager", basePackages = {"com.twoDB.teacher.repo"})

public class TeacherDbConfig {
  @Bean(name = "teacherDataSource")
  @ConfigurationProperties(prefix = "teacher.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "teacherEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean teacherEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("teacherDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.twoDB.teacher.domain").persistenceUnit("teacher")
        .build();
  }

  @Bean(name = "teacherTransactionManager")
  public PlatformTransactionManager teacherTransactionManager(
      @Qualifier("teacherEntityManagerFactory") EntityManagerFactory teacherEntityManagerFactory) {
    return new JpaTransactionManager(teacherEntityManagerFactory);
  }

}
