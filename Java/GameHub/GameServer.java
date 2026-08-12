import com.sun.net.httpserver.*;
import java.net.InetSocketAddress;
import java.util.concurrent.*;
import java.util.*;
import java.io.*;

public class GameServer {
    // Sesiones en memoria (sin persistencia)
    private static final Map<String, GameSession> sessions = new ConcurrentHashMap<>();
    private static final Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Endpoints
        server.createContext("/api/session/create", new CreateSessionHandler());
        server.createContext("/api/session/", new SessionHandler());
        server.createContext("/api/update", new UpdateHandler());
        
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
        System.out.println("🚀 Servidor Java corriendo en http://localhost:8080");
        System.out.println("📋 Sesiones activas: " + sessions.size());
    }

    // ============ MODELO ============
    static class GameSession {
        String id;
        String playerName;
        int lives = 3;
        int score = 0;
        long lastActive = System.currentTimeMillis();

        GameSession(String id, String playerName) {
            this.id = id;
            this.playerName = playerName;
        }
    }

    // ============ UTILS ============
    static void sendJson(HttpExchange ex, int code, Object data) throws IOException {
        String json = gson.toJson(data);
        ex.getResponseHeaders().set("Content-Type", "application/json");
        ex.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        ex.sendResponseHeaders(code, json.length());
        ex.getResponseBody().write(json.getBytes());
        ex.close();
    }

    static String readBody(HttpExchange ex) throws IOException {
        return new BufferedReader(new InputStreamReader(ex.getRequestBody()))
                .lines().reduce("", (a, b) -> a + b);
    }

    // ============ HANDLERS ============
    static class CreateSessionHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!ex.getRequestMethod().equals("POST")) {
                sendJson(ex, 405, Map.of("error", "Method not allowed"));
                return;
            }
            Map<String, String> body = gson.fromJson(readBody(ex), Map.class);
            String name = body.getOrDefault("playerName", "Anónimo");
            String id = UUID.randomUUID().toString().substring(0, 8);
            
            GameSession session = new GameSession(id, name);
            sessions.put(id, session);
            
            System.out.println("✅ Nueva sesión: " + name + " (" + id + ")");
            sendJson(ex, 200, session);
        }
    }

    static class SessionHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            ex.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            String path = ex.getRequestURI().getPath();
            String id = path.replace("/api/session/", "");
            
            GameSession s = sessions.get(id);
            if (s == null) {
                sendJson(ex, 404, Map.of("error", "Sesión no encontrada"));
                return;
            }
            sendJson(ex, 200, s);
        }
    }

    static class UpdateHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!ex.getRequestMethod().equals("POST")) {
                sendJson(ex, 405, Map.of("error", "Method not allowed"));
                return;
            }
            Map<String, Object> body = gson.fromJson(readBody(ex), Map.class);
            String id = (String) body.get("sessionId");
            Integer lives = body.containsKey("lives") ? ((Double) body.get("lives")).intValue() : null;
            Integer score = body.containsKey("score") ? ((Double) body.get("score")).intValue() : null;

            GameSession s = sessions.get(id);
            if (s == null) {
                sendJson(ex, 404, Map.of("error", "Sesión no encontrada"));
                return;
            }
            if (lives != null) s.lives = lives;
            if (score != null) s.score = score;
            s.lastActive = System.currentTimeMillis();
            
            sendJson(ex, 200, s);
        }
    }

    // Mini-Gson para no depender de librerías externas
    static class Gson {
        String toJson(Object o) {
            if (o instanceof GameSession) {
                GameSession s = (GameSession) o;
                return String.format("{\"id\":\"%s\",\"playerName\":\"%s\",\"lives\":%d,\"score\":%d}",
                    s.id, s.playerName, s.lives, s.score);
            }
            if (o instanceof Map) {
                Map<?,?> m = (Map<?,?>) o;
                StringBuilder sb = new StringBuilder("{");
                boolean first = true;
                for (Map.Entry<?,?> e : m.entrySet()) {
                    if (!first) sb.append(",");
                    sb.append("\"").append(e.getKey()).append("\":");
                    if (e.getValue() instanceof String) sb.append("\"").append(e.getValue()).append("\"");
                    else sb.append(e.getValue());
                    first = false;
                }
                return sb.append("}").toString();
            }
            return "{}";
        }
        @SuppressWarnings("unchecked")
        <T> T fromJson(String json, Class<T> clazz) {
            Map<String, Object> map = new HashMap<>();
            json = json.replace("{", "").replace("}", "").replace("\"", "");
            for (String pair : json.split(",")) {
                String[] kv = pair.split(":", 2);
                if (kv.length == 2) {
                    String v = kv[1].trim();
                    if (v.matches("\\d+")) map.put(kv[0].trim(), Integer.parseInt(v));
                    else if (v.matches("\\d+\\.\\d+")) map.put(kv[0].trim(), Double.parseDouble(v));
                    else map.put(kv[0].trim(), v);
                }
            }
            return (T) map;
        }
    }
}