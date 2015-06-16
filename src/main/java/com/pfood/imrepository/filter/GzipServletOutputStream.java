package com.pfood.imrepository.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GzipServletOutputStream extends ServletOutputStream{
	
	GZIPOutputStream internalGzipOS;
	
	GzipServletOutputStream(ServletOutputStream sos) throws IOException{
		this.internalGzipOS = new GZIPOutputStream(sos);
	}

	@Override
	public void write(int arg0) throws IOException {
		internalGzipOS.write(arg0);		
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}

}
