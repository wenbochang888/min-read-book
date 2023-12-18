package com.tianya.dao;

import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

/**
 * @author changwenbo
 * @date 2023/2/28 10:03
 */
//@DAO(catalog = "hikari")
public interface TestDAO {
	@SQL("select f_element_id from cashpay_familybank.t_element where f_element_id=:limit")
	public long select(@SQLParam("limit") long limit);

	@SQL("select f_element_status from cashpay_familybank.t_element where f_element_status=:limit")
	Integer selectStatus(@SQLParam("limit") int limit);

	@SQL("UPDATE cashpay_familybank.t_element SET f_extension=2 WHERE f_element_status=1")
	int update();

	@SQL("select count(*) from cashpayuser_70.t_bank_card_670")
	public long selectManyTables();

}
