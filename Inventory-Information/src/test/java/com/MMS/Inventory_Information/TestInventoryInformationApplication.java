package com.MMS.Inventory_Information;

import org.springframework.boot.SpringApplication;

public class TestInventoryInformationApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryInformationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
