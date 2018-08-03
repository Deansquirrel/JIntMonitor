package com.yuansong.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.google.gson.Gson;

public class ExceptionHandler extends HandlerExceptionResolverComposite {
	
	private final Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	private final Gson mGson = new Gson();
	
	public ModelAndView resolveException(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) {
		
		logger.error(ex.getMessage());
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		StringWriter sw = new StringWriter();   
        PrintWriter pw = new PrintWriter(sw, true);   
        ex.printStackTrace(pw);   
        pw.flush();   
        sw.flush();   
        
        Map<String,String> data = new HashMap<String,String>();
		data.put("errCode", "404");
		data.put("errDesc","Page not found.");
			
		data.put("errCode", "503");
		data.put("errDesc", ex.getMessage());
		data.put("errPath", sw.toString());
		
		model.put("info", mGson.toJson(data));
		
        return new ModelAndView("errorPage",model);   
    }

}
