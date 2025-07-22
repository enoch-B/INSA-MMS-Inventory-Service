package com.MMS.Inventory_Information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class InventoryInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryInformationApplication.class, args);
	}

}
