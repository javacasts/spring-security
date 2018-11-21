package com.example.demo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoFilter implements Filter {

  private Logger logger;

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    logger = LoggerFactory.getLogger(DemoFilter.class);
  }

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain chain) throws IOException, ServletException {
    final long before = System.currentTimeMillis();
    chain.doFilter(request, response);
    final long after = System.currentTimeMillis();
    logger.info("Request {} took {} ms.", ((HttpServletRequest) request).getRequestURI(),
        after - before);
  }

  @Override
  public void destroy() {
    // Nothing to destroy
  }

}
