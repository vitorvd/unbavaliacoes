package br.com.unbhelp.contexto;

import br.com.unbhelp.models.Estudante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ContextoManager {

    private static final String MANAGER_CONTEXT = "CONTEXT.UNBAVALIACOES";

    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void init(){
        this.servletContext.setAttribute(MANAGER_CONTEXT, new HashMap<String, Object>());
    }

    public void adicionarToken(String token, Object user) {
        this.getMap().put(token, user);
    }

    public void removerToken(String token) {
        List<String> tokens = this.getMap().entrySet().stream()
                .filter(tokenTemp -> (tokenTemp.getKey()).equals(token))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        tokens.forEach(tokenTemp -> getMap().remove(tokenTemp));
    }

    public boolean existeToken(String token){
        return this.getMap().containsKey(token);
    }

    public Estudante getEstudantePorToken(String token) {
        return (Estudante) this.getMap().get(token);
    }

    private Map<String, Object> getMap() {
        return (Map<String, Object>) this.servletContext.getAttribute(MANAGER_CONTEXT);
    }

}
