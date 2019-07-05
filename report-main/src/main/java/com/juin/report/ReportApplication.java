package com.juin.report;

import com.juin.report.core.DataAuthenticationPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.Properties;

@SpringBootApplication()
@ImportResource("spring/appContext.xml")
public class ReportApplication {
	private static final Logger logger = LoggerFactory.getLogger(ReportApplication.class);
	public static void main(String[] args) {
		logger.debug("begin to start-up [test]");
		Properties properties = new Properties();
		DataAuthenticationPlugin dataAuthenticationPlugin = new DataAuthenticationPlugin();
		dataAuthenticationPlugin.setProperties(properties);
		//
		SpringApplication.run(ReportApplication.class, args);
		logger.debug("start-up [test] success !!!");
	}
}
