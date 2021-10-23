package service;

import model.Customer;
import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    Collection<Customer> customers = new HashSet<Customer>();
    private static CustomerService customerservice = null;

    public static CustomerService getInstance() {
        if(null == customerservice) {
            customerservice = new CustomerService();
        }
        return customerservice;
    }
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if(customer.getEmail().equalsIgnoreCase(customerEmail)) {
                return customer;
            }
        }
        return null;
    }
    public Collection<Customer> getAllCustomers() {
        return customers;
    }


}
