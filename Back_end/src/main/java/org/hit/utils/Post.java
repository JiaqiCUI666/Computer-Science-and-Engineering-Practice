//package org.hit.utils;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//import org.hit.pojo.Dish;
//import org.hit.pojo.PreOrder;
//import org.hit.service.DishService;
//import org.hit.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class Post {
//
//    private static DishService dishService = new DishService();
//    private static OrderService orderService = new OrderService();
//
//    @Autowired
//    private DishService dishService1;
//    private OrderService orderService1;
//
//
//
//    @PostConstruct
//    public void init() {
//        dishService = dishService1;
//        orderService = orderService1;
//
//        List<Dish> dishList = dishService.getDishList();
//        for (Dish dish : dishList) {
//            PreOrder thisPreOrder = orderService.getPreOrderById(dish.getId());
//            if (thisPreOrder == null){
//                PreOrder preOrder = new PreOrder();
//                preOrder.setId(dish.getId());
//                preOrder.setStock(0);
//                preOrder.setOrdernum(0);
//                preOrder.setCanteenid(0);
//                preOrder.setTime(null);
//                orderService.insertPreOrder(preOrder);
//                System.out.println("对于菜品"+dish.getId()+"的预订单初始化完成");
//            }
//        }
//        System.out.println("PostConstruct执行完毕");
//    }
//}
