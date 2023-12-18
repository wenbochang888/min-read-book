package com.tianya.conf.datasource;

/**
 * @author changwenbo
 * @date 2023/3/24 15:58
 */
//@Configuration
//@MapperScan(basePackages = "com.tianya.dao2", sqlSessionFactoryRef = "druidSqlSessionFactory")
//public class DruidDatasourceConfig {
//
//
//	/**
//	 * druid + mybatis
//	 */
//
//	@Primary
//	@Bean("druidDataSource")
//	@ConfigurationProperties("spring.datasource.druid")
//	public DataSource druidDataSource(){
//		DataSource build = DruidDataSourceBuilder.create().build();
//		return build;
//	}
//
//	@Bean(name = "druidSqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
//		MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
//		sessionFactoryBean.setDataSource(dataSource);
//		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//				.getResources("classpath:mybatis/mapper/slave/*.xml"));
//
//
//
//		MybatisConfiguration configuration = new MybatisConfiguration();
//		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//		configuration.addInterceptor(interceptor);
//
//
//		return sessionFactoryBean.getObject();
//	}
//
//}
