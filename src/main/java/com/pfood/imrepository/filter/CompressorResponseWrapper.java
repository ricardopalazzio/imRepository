package com.pfood.imrepository.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CompressorResponseWrapper extends HttpServletResponseWrapper{
	
	private GzipServletOutputStream servletGzipOS = null;
	private PrintWriter pw = null;
	private Object streamUsed = null;
	
	public CompressorResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public GZIPOutputStream getGzipOutputStream() throws IOException {
		if (servletGzipOS == null)
			servletGzipOS = new GzipServletOutputStream(getResponse().getOutputStream());
		
		return this.servletGzipOS.internalGzipOS;
	}
	
	public ServletOutputStream getOutputStream() throws IOException {
		
		if ((streamUsed != null) && (streamUsed == pw)){
			throw new IllegalStateException();
		}
		
		if (servletGzipOS == null) {
			servletGzipOS = new GzipServletOutputStream(getResponse().getOutputStream());
			streamUsed = servletGzipOS;
		}
		
		return servletGzipOS;
	}
	
	public PrintWriter getWriter() throws IOException {
		
		if ((streamUsed != null) && (streamUsed == pw)){
			throw new IllegalStateException();
		}
		
		if (pw == null) {
			servletGzipOS = new GzipServletOutputStream(getResponse().getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(servletGzipOS, getResponse().getCharacterEncoding());
			
			pw = new PrintWriter(osw);
			streamUsed = pw;
		}
		
		return pw;
	}
	
}
