package designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ObserverPatternEx01 {
    public static void main(String[] args) {
        problem1();
    }

    /*
     * 문제 1, 고객이 
     */
    private static void problem1() {
        //가게와 고객들
        Store store = new Store("신발 가게");
        Customer c1 = new Customer(store);
        Customer c2 = new Customer(store);
        Customer c3 = new Customer(store);

        
        
        Thread t1 = new Thread(runnableTemplate(() -> c1.checkNewProduct()));
        Thread t2 = new Thread(runnableTemplate(() -> c2.checkNewProduct()));
        Thread t3 = new Thread(runnableTemplate(() -> c3.checkNewProduct()));
        t1.start();
        t2.start();
        t3.start();
        
        //신상품 입고
        store.addProduct(new Product("멋진 운동화"));
    }

    static Runnable runnableTemplate(Runnable run){
        return () -> {
            for(int i = 0; i < 5; i++){
                run.run();
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                } catch (InterruptedException e) {
                    System.exit(0);
                }
            }
        };
    }

}


/*
고객들이 존재하며, 고객들 일부만 신상에 관심이 있다.
*/
class Customer {
    Store store;
    
    public Customer(Store store) {
        this.store = store;
    }

    void checkNewProduct(){
        if(store.existNewProduct()){
            System.out.println("야호! 이 신상은 내꺼 : " + store.getNewProduct());
        } else {
            System.out.println("신상은 놓쳤어....");
        }
    }
}

class Store {
    String name;
    List<Product> products = new ArrayList<>();
    boolean existNewProduct = false;

    public Store(String name) {
        this.name = name;
    }

    void addProduct(Product product){
        products.add(product);
        existNewProduct = true;
    }

    boolean existNewProduct(){
        return existNewProduct;
    }

    Product getNewProduct(){
        //누가 신상품 가져감
        if(!existNewProduct){
            return null;
        }

        existNewProduct = false;

        return products.get(products.size() - 1);
    }
}
class Product {
    String name;

    public Product(String name) {
        this.name = name;
    }
    
}