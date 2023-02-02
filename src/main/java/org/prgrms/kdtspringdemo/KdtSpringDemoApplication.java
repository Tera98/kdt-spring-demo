package org.prgrms.kdtspringdemo;

import org.prgrms.kdtspringdemo.order.OrderProperties;
import org.prgrms.kdtspringdemo.voucher.FixedAmountVoucher;
import org.prgrms.kdtspringdemo.voucher.JdbcVoucherRepository;
import org.prgrms.kdtspringdemo.voucher.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.text.MessageFormat;
import java.util.UUID;

@SpringBootApplication
@ComponentScan(
		basePackages = {"org.prgrms.kdtspringdemo.voucher", "org.prgrms.kdtspringdemo.order"}
)
public class KdtSpringDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(KdtSpringDemoApplication.class);

	public static void main(String[] args) {

		var applicationContext = SpringApplication.run(KdtSpringDemoApplication.class, args);
		var orderProperties = applicationContext.getBean(OrderProperties.class);
		logger.error("logger name => {}", logger.getName());
		logger.warn("version -> {}", orderProperties.getVersion());
		logger.warn("minimumOrderAmount -> {}", orderProperties.getMinimumOrderAmount());
		logger.warn("supportVendors -> {}", orderProperties.getSupportVendors());
		logger.warn("description -> {}", orderProperties.getDescription());
	}
}
