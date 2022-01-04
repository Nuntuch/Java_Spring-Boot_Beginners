//package com.appsdeveloperblog.app.ws.temp;
//
//import lombok.Data;
//
//@Data
//public class JsonToObjDto {
//	
//	@Data
//	public class Order {
//		  private int id;
//		  private Customer customer;
//		}
//
//	@Data
//	public class Customer {
//		  private int id;        
//		  private Address address;
//		}
//	
//	@Data
//	public class Address {
//		  private String street;
//		  private String city;
//		}
//	
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////import lombok.Data;
////import lombok.Getter;
////import lombok.Setter;
////
////public class JsonToObjDto {
////	// Assume getters and setters are present
////
//////	@Data
////	public class Order {
////		private int id;
////		private Customer customer;
////
////		public Order(int id, Customer customer) {
////			super();
////			this.id = id;
////			this.customer = customer;
////		}
////
////		// int id;
//////	  Customer customer;
////		public int getId() {
////			return id;
////		}
////
////		public void setId(int id) {
////			this.id = id;
////		}
////
////		public Customer getCustomer() {
////			return customer;
////		}
////
////		public void setCustomer(Customer customer) {
////			this.customer = customer;
////		}
////	}
////
//////	@Getter
//////	@Setter
////	public class Customer {
////		private int id;
////		private Address address;
////
////		public Customer(int id, Address address) {
////			super();
////			this.id = id;
////			this.address = address;
////		}
////
////		public int getId() {
////			return id;
////		}
////
////		public void setId(int id) {
////			this.id = id;
////		}
////
////		public Address getAddress() {
////			return address;
////		}
////
////		public void setAddress(Address address) {
////			this.address = address;
////		}
////	}
////
//////	@Getter
//////	@Setter
////	public class Address {
////		private String street;
////		private String city;
////
////		public Address(String street, String city) {
////			super();
////			this.street = street;
////			this.city = city;
////		}
////
////		public String getStreet() {
////			return street;
////		}
////
////		public void setStreet(String street) {
////			this.street = street;
////		}
////
////		public String getCity() {
////			return city;
////		}
////
////		public void setCity(String city) {
////			this.city = city;
////		}
////	}
////}
