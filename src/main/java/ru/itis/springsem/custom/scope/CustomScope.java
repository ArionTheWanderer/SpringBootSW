package ru.itis.springsem.custom.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomScope implements Scope {

    private Map<String, Integer> pageVisitCounter = new HashMap<>();
    private Map<String, Object> beans = new HashMap<>();

    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        if (beans.containsKey(s)) {
            if (s.toLowerCase().contains("controller")) {
                Object bean = beans.get(s);
                pageVisitCounter.replace(s, pageVisitCounter.get(s) + 1);
                System.out.println("Кол-во посещений " + s + " = " + pageVisitCounter.get(s));
                return bean;
            } else {
                Object bean = beans.get(s);
                return bean;
            }
        } else {

            if (s.toLowerCase().contains("controller")) {
                Object o = objectFactory.getObject();
                beans.put(s, o);
                pageVisitCounter.put(s, 1);
                System.out.println("Кол-во посещений " + s + " = " + pageVisitCounter.get(s));
                return o;
            }
            Object o = objectFactory.getObject();
            beans.put(s, o);
            return o;
        }
    }

    @Override
    public Object remove(String s) {
        if (pageVisitCounter.containsKey(s)) {
            pageVisitCounter.remove(s);
        }
        return beans.remove(s);
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
