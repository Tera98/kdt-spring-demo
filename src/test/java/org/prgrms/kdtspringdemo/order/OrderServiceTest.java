package org.prgrms.kdtspringdemo.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.prgrms.kdtspringdemo.voucher.FixedAmountVoucher;
import org.prgrms.kdtspringdemo.voucher.MemoryVoucherRepository;
import org.prgrms.kdtspringdemo.voucher.Voucher;
import org.prgrms.kdtspringdemo.voucher.VoucherService;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    @DisplayName("오더가 생성되야한다, (stub)")
    void createOrder() {
        // GIVEN
        var voucherRepository = new MemoryVoucherRepository();
        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
        voucherRepository.insert(fixedAmountVoucher);
        var sut = new OrderService(new VoucherService(voucherRepository), new MemoryOrderRepository());

        //WHEN
        var order = sut.createOrder(UUID.randomUUID(), List.of(new OrderItem(UUID.randomUUID(), 200, 1)), fixedAmountVoucher.getVoucherId());

        //THEN
        assertThat(order.totalAmount(), is(100L));
        assertThat(order.getVoucher().isEmpty(), is(false));
        assertThat(order.getVoucher().get().getVoucherId(), is(fixedAmountVoucher.getVoucherId()));
        assertThat(order.getOrderStatus(), is(OrderStatus.ACCEPTED));
    }

//    @Test
//    @DisplayName("오더가 생성되야한다, (mock)")
//    void createOrderByMock() {
//
//        //GIVEN
//        var voucherServiceMock = mock(VoucherService.class);
//        var orderRepositoryMock = mock(OrderRepository.class);
//        var fixedAmountVoucher = new FixedAmountVoucher(UUID.randomUUID(), 100);
//        when(voucherServiceMock.getVoucher(fixedAmountVoucher.getVoucherId())).thenReturn(fixedAmountVoucher);
//        var sut = new OrderService(voucherServiceMock, orderRepositoryMock);
//
//
//        //WHEN
//        var order = sut.createOrder(UUID.randomUUID(), List.of(new OrderItem(UUID.randomUUID(), 200, 1)), fixedAmountVoucher.getVoucherId());
//
//        //THEN
//        assertThat(order.totalAmount(), is(100L));
//        assertThat(order.getVoucher().isEmpty(), is(false));
//        verify(voucherServiceMock).getVoucher(fixedAmountVoucher.getVoucherId());
//        verify(orderRepositoryMock).insert(order);
//        verify(voucherServiceMock).useVoucher(fixedAmountVoucher);
//
//    }


}