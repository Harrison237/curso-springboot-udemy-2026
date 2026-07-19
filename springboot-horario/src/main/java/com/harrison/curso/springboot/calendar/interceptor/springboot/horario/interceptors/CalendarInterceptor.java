package com.harrison.curso.springboot.calendar.interceptor.springboot.horario.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component("calendarInterceptor")
public class CalendarInterceptor implements HandlerInterceptor {

    @Value("${config.calendar.open}")
    private Integer open;
    @Value("${config.calendar.close}")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int hour = LocalDateTime.now(ZoneId.systemDefault()).getHour();
        System.out.println(hour);

        StringBuilder message = new StringBuilder();
        if (hour >= open && hour < close) {
            message.append("Bienvenidos al horario de atención a clientes.");
            message.append(" Atendemos desde las ");
            message.append(open);
            message.append(" hrs");
            message.append(" hasta las ");
            message.append(close);
            message.append(" hrs.");
            message.append(" Gracias por su visita!");
            request.setAttribute("message", message.toString());
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        message.append("Cerrado, fuera del horario de servicio. ");
        message.append("Por favor visitenos desde las ");
        message.append(open);
        message.append(" y las ");
        message.append(close);
        message.append(" hrs. Gracias!");
        data.put("message", message.toString());
        data.put("date", LocalDateTime.now(ZoneId.systemDefault()).toString());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.getWriter().write(mapper.writeValueAsString(data));

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }
}
