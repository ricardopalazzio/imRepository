package com.pfood.imrepository.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CompressorFilter
 */
public class CompressorFilter implements Filter {
	
	private ServletContext ctx;
	private FilterConfig cfg;

    public CompressorFilter() {
        // TODO Auto-generated constructor stub
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
		this.cfg = fConfig;
		this.ctx = cfg.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String validEncodings = req.getHeader("Accept-Encoding");
		
		if (validEncodings != null && validEncodings.indexOf("gzip") > -1) {	
			CompressorResponseWrapper respWrapper = new CompressorResponseWrapper(res);
			respWrapper.setHeader("Content-Encoding", "gzip");
		
			chain.doFilter(req, respWrapper);
			
			GZIPOutputStream gzos = respWrapper.getGzipOutputStream();
			gzos.finish();
			
		} else {
			chain.doFilter(req, res);
		}
	}
	
	public void destroy() {
		cfg = null;
		ctx = null;
	}

}
