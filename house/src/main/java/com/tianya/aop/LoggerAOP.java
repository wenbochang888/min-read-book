package com.tianya.aop;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianya.util.GsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author changwenbo
 * @date 2023/3/8 16:55
 */
@Aspect
@Component
public class LoggerAOP {
	private static final Logger logger = LoggerFactory.getLogger(LoggerAOP.class);
	private static final String SERVLET_PARAM = "httpservlet";

	@Pointcut("execution (public * com.tianya.controller..*(..))")
	public void pointcutLogger() {}

	@Around("pointcutLogger()")
	public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String s = UUID.randomUUID().toString();
		MDC.put("sessionId", s);

		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String ip = getRequestIP();
		Stopwatch stopwatch = Stopwatch.createStarted();
		Object resp = null;

		try {
			String param = buildParam(joinPoint);

			logger.info("request className = {}, method = {}, ip = {}, param = {}", className, methodName, ip, param);

			resp = joinPoint.proceed();

			long duration = stopwatch.elapsed(TimeUnit.MILLISECONDS);

			Set<String> ignorePrintMethods = Sets.newHashSet("getImage");
			if (ignorePrintMethods.contains(methodName)) {
				logger.info("response className = {}, method = {}, resp = {}, cost = {}ms", className, methodName, ignorePrintMethods, duration);
			} else {
				logger.info("response className = {}, method = {}, resp = {}, cost = {}ms", className, methodName, buildResp(resp), duration);
			}
		}

		catch (Throwable e) {
			logger.error("logger request className = {}, method = {} fail message = {} ",
					className, methodName, e.getMessage(), e);
			throw e;
		} finally {
			MDC.clear();
		}

		return resp;
	}

	private Object buildResp(Object resp) {
		if (resp == null) {
			return "";
		}

		if (resp instanceof String) {
			try {
				return JsonParser.parseString((String) resp);
			} catch (Exception e) {
				return resp;
			}
		}

		return GsonUtils.toJson(resp);
	}

	private String buildParam(ProceedingJoinPoint joinPoint) {
		try {
			JsonObject jsonObject = new JsonObject();

			Object[] args = joinPoint.getArgs();
			// 包装类
			if (args != null && args.length == 1) {
				return GsonUtils.toJson(joinPoint.getArgs());
			}

			String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
			int min = Math.min(args == null ? 0 : args.length,
					parameterNames == null ? 0 : parameterNames.length);

			int pos = -1;
			for (int i = 0; i < min; i++) {
				if (parameterNames[i].toLowerCase().contains(SERVLET_PARAM)) {
					continue;
				}
				jsonObject.addProperty(GsonUtils.toJson(parameterNames[i]), GsonUtils.toJson(args[i]));
				pos = i;
			}

			// 兼容httpServlet参数
			if (jsonObject != null && pos != -1 && jsonObject.size() == 1) {
				return GsonUtils.toJson(args[pos]);
			}

			return jsonObject.toString();

		} catch (Exception e) {
			return GsonUtils.toJson(joinPoint.getArgs());
		}
	}

	private String getRequestIP() {
		String ip = "";
		try {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			ip = request.getRemoteAddr();
		} catch (Exception e) {
			logger.warn("get ip error = {}", e.getMessage(), e);
		}
		return ip;
	}
}
