package br.com.igovargas.dao;

import br.com.igovargas.domain.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerMapDAO implements ICustomerDAO {

    private Map<Long, Customer> map;

    public CustomerMapDAO() {
        this.map = new HashMap<>();
    }

    @Override
    public Boolean register(Customer customer) {
        if (this.map.containsKey(customer.getCpf())) {
            return false;
        }
        this.map.put(customer.getCpf(), customer);
        return true;
    }

    @Override
    public void exclude(Long cpf) {
        Customer registeredCustomer = this.map.get(cpf);

        if (registeredCustomer != null){
            this.map.remove(registeredCustomer.getCpf(), registeredCustomer);
        }
    }

    @Override
    public void change(Customer customer) {
        Customer registeredCustomer = this.map.get(customer.getCpf());

        if (registeredCustomer != null) {
            registeredCustomer.setName(customer.getName());
            registeredCustomer.setCell(customer.getCell());
            registeredCustomer.setNumber(customer.getNumber());
            registeredCustomer.setAddress(customer.getAddress());
            registeredCustomer.setCity(customer.getCity());
            registeredCustomer.setState(customer.getState());
        }
    }

    @Override
    public Customer consult(Long cpf) {
        return this.map.get(cpf);

    }

    @Override
    public Collection<Customer> searchAll() {
        return this.map.values();

    }

}
