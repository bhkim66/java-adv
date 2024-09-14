//package thread.executor.test;
//
//import thread.executor.CallableTask;
//
//import java.util.concurrent.*;
//
//import static util.MyLogger.log;
//import static util.ThreadUtils.sleep;
//
//public class NewOrderService2 {
//    private final ExecutorService es = Executors.newFixedThreadPool(3);
//    public void order(String orderNo){
//
//        CallableTask future1 = new CallableTask(new InventoryWork(orderNo));
//        CallableTask future2 = new CallableTask() es.submit(new ShippingWork(orderNo));
//        CallableTask future3 = es.submit(new AccountingWork(orderNo));
//
//        String orderNo1 = null;
//        String orderNo2 = null;
//        String orderNo3 = null;
//        try {
//            orderNo1 = future1.get();
//            orderNo2 = future2.get();
//            orderNo3 = future3.get();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 결과 확인
//        if (orderNo.equals(orderNo1) && orderNo.equals(orderNo2) && orderNo.equals(orderNo3)) {
//            log("모든 주문 처리가 성공적으로 완료되었습니다.");
//        } else {
//            log("일부 작업이 실패했습니다.");
//        }
//    }
//    static class InventoryWork implements Callable<String> {
//        private final String orderNo;
//        public InventoryWork(String orderNo) {
//            this.orderNo = orderNo;
//        }
//
//        @Override
//        public String call() {
//            log("재고 업데이트: " + orderNo);
//            sleep(1000);
//            return orderNo;
//        }
//    }
//    static class ShippingWork implements Callable<String> {
//        private final String orderNo;
//        public ShippingWork(String orderNo) {
//            this.orderNo = orderNo;
//        }
//        @Override
//        public String call() {
//            log("배송 시스템 알림: " + orderNo);
//            sleep(1000);
//            return orderNo;
//        }
//    }
//    static class AccountingWork implements Callable<String> {
//        private final String orderNo;
//        public AccountingWork(String orderNo) {
//            this.orderNo = orderNo;
//        }
//        @Override
//        public String call() {
//            log("회계 시스템 업데이트: " + orderNo);
//            sleep(1000);
//            return orderNo;
//        }
//    }
//}
