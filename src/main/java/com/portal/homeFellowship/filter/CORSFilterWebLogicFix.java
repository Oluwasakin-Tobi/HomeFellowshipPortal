package com.portal.homeFellowship.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CORSFilterWebLogicFix implements Filter {
	Logger LOGGER = LoggerFactory.getLogger(CORSFilterWebLogicFix.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		//LOGGER.info("Filtering on...........................................................");
		String pageRequested = ((HttpServletRequest) req).getRequestURL().toString();
		if (pageRequested == null || pageRequested.endsWith("/SAYS")) {

			((HttpServletResponse) res).sendRedirect("/SAYS/");
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
