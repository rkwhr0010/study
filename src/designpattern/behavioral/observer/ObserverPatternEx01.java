package designpattern.behavioral.observer;

import java.util.concurrent.ThreadLocalRandom;

public class ObserverPatternEx01 {
    public static void main(String[] args) {
        problem1();
    }

    /*
     * 문제 1, 고객이 지속적으로 신상품 입고에 관심을 가져야 한다.
     */
    private static void problem1() {
        //가게와 고객들
        Store store = new Store("신발 가게");
        Customer c1 = new Customer(store);
        Customer c2 = new Customer(store);
        Customer c3 = new Customer(store);
        
        Thread t1 = new Thread(runnableTemplate(c1::checkNewProduct));
        Thread t2 = new Thread(runnableTemplate(c2::checkNewProduct));
        Thread t3 = new Thread(runnableTemplate(c3::checkNewProduct));

        t1.start();
        t2.start();
        t3.start();
        
        //신상품 입고
        store.addProduct(new Product("멋진 운동화"));
    }

    static Runnable runnableTemplate(Runnable run){
        return run::run;
    }

}
class Customer {
    static Long serial = 1L;
    Long id;
    Store store;
    
    public Customer(Store store) {
        id = serial++;
        this.store = store;
    }

    void checkNewProduct() {
        System.out.println(id++ + " 신상품 입고 대기 중 !!!");

        while(true && !store.existNewProduct()){
            System.out.println(id+ " 야호! 구매했다. " + store.getNewProduct());
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (Exception e) {}

            return;
        }
    }
}

class Store {
    String name;
    Product product; 
    boolean existNewProduct = false;

    public Store(String name) {
        this.name = name;
    }

    void addProduct(Product product){
        this.product = product;
        existNewProduct = true;
    }

    boolean existNewProduct(){
        return existNewProduct;
    }

    Product getNewProduct(){
        return product;
    }
}
class Product {
    String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + "]";
    }
}