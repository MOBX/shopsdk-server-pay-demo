package com.mob.etrade.server.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

/**
 * spring boot 应用启动类
 * @author yunkai(xianyi)
 * @date 2017/12/14
 */

@SpringBootApplication
public class EtradeServerDemoApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(EtradeServerDemoApplication.class)
				.web(true)
				.logStartupInfo(true)
				.run(args);
	}

	/**
	 * 健康检查
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean healthAction() {
		return new ServletRegistrationBean(new HttpServlet() {

			private static final long serialVersionUID = -55776623249934740L;

			@Override
			public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				Map<String, Object> params = new HashMap<>();
				params.put("status", 200);
				params.put("time", format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss:SSS"));

				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(new ObjectMapper().writeValueAsString(params));
				out.flush();
				out.close();
			}

			@Override
			public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
			}
		}, "/health");
	}
}
