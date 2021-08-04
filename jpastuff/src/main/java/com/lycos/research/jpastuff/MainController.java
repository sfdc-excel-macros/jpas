package com.lycos.research.jpastuff;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/main")
    @ResponseBody
    public String doMain(@RequestParam(name="foo", defaultValue="xyzzy") String foo) {
        ArrayList<String> values = new ArrayList<String>();
        values.add(foo);
        Query q = em.createNativeQuery("SELECT * FROM foo WHERE b IN :values").setParameter("values", values);
        List<Object[]> result = q.getResultList();
        return "foo: " + foo + " resultCount: " + result.size();
    }
    @GetMapping("/vuln")
    @ResponseBody
    public String doVuln(@RequestParam(name="foo", defaultValue="xyzzy") String foo) {
        Query q = em.createNativeQuery("SELECT * FROM foo WHERE b = '" + foo + "'");    
        List<Object[]> result = q.getResultList();
        return "foo: " + foo + " resultCount: " + result.size();
    }
}

