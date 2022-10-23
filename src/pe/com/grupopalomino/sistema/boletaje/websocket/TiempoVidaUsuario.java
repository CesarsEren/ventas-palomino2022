package pe.com.grupopalomino.sistema.boletaje.websocket;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.Endpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

@ServerEndpoint("/finalizasesioncliente")
public class TiempoVidaUsuario implements ServerApplicationConfig {

	static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	@OnOpen
    public void openConnection(Session session) {
        System.out.println("iniciando ....");
        queue.add(session);
    }
	
	@OnClose
    public void closedConnection(Session session) {
        System.out.println("cerrando .....");
        queue.remove(session);
    }
	
	@OnError
    public void error(Session session, Throwable t) {
        queue.remove(session);
    }
	
	@OnMessage
    public void onMessage(String message, Session session) {
        if (queue.contains(session)) {
            
        }
    }

	@Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<ServerEndpointConfig>();

        return result;
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {

        Set<Class<?>> results = new HashSet<Class<?>>();
        for (Class<?> clazz : scanned) {
            results.add(clazz);
        }
        return results;
    }
	
}
