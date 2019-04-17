package com.roryharford.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	
	//Only thing that changes is the type of class methods stay the same 
	//Have all the crud Statements
	//Only have to implement custom statements
	
	
}
