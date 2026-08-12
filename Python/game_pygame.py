import pygame
import requests
import json
import random

API = "http://localhost:8080"

# Inicializar Pygame
pygame.init()
WIDTH, HEIGHT = 400, 600
screen = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Esquivador Espacial - Pygame + Java")
clock = pygame.time.Clock()
font = pygame.font.SysFont("Arial", 24)
font_big = pygame.font.SysFont("Arial", 36)

# Colores
COLOR_BG = (15, 52, 96)
COLOR_PLAYER = (233, 69, 96)
COLOR_OBSTACLE = (243, 156, 18)
COLOR_TEXT = (238, 238, 238)
COLOR_PANEL = (22, 33, 62)

def crear_sesion(nombre):
    """Crea sesión en el backend Java"""
    try:
        res = requests.post(f"{API}/api/session/create", 
                          json={"playerName": nombre},
                          timeout=5)
        return res.json()
    except Exception as e:
        print(f"Error conectando a Java: {e}")
        return None

def actualizar_java(session_id, lives, score):
    """Sincroniza estado con Java"""
    try:
        requests.post(f"{API}/api/update",
                     json={"sessionId": session_id, "lives": lives, "score": score},
                     timeout=2)
    except:
        pass

def main():
    # Login en consola
    print("=" * 40)
    print("🎮 ESQUIVADOR ESPACIAL")
    print("=" * 40)
    nombre = input("Ingresa tu nombre: ").strip() or "Anónimo"
    
    print(f"\nConectando a Java backend en {API}...")
    session = crear_sesion(nombre)
    
    if not session:
        print("❌ No se pudo conectar al servidor Java")
        print("Asegúrate de ejecutar: java GameServer")
        return
    
    session_id = session["id"]
    player_name = session["playerName"]
    lives = session["lives"]
    score = session["score"]
    
    print(f"✅ Sesión creada: {session_id}")
    print(f"👤 Jugador: {player_name}")
    print("Usa ← → para moverte. Cierra la ventana para salir.\n")
    
    # Estado del juego
    player = {"x": 180, "y": 540, "w": 40, "h": 40, "speed": 5}
    obstacles = []
    frame = 0
    running = True
    game_over = False
    
    while running:
        clock.tick(60)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        
        if not game_over:
            keys = pygame.key.get_pressed()
            if keys[pygame.K_LEFT] and player["x"] > 0:
                player["x"] -= player["speed"]
            if keys[pygame.K_RIGHT] and player["x"] < WIDTH - player["w"]:
                player["x"] += player["speed"]
            
            # Generar obstáculos
            frame += 1
            if frame % 60 == 0:
                obstacles.append({
                    "x": random.randint(0, WIDTH - 30),
                    "y": -30,
                    "w": 30,
                    "h": 30,
                    "speed": random.uniform(3, 5)
                })
            
            # Mover obstáculos y detectar colisiones
            for obs in obstacles[:]:
                obs["y"] += obs["speed"]
                
                # Colisión AABB
                if (player["x"] < obs["x"] + obs["w"] and
                    player["x"] + player["w"] > obs["x"] and
                    player["y"] < obs["y"] + obs["h"] and
                    player["y"] + player["h"] > obs["y"]):
                    
                    lives -= 1
                    obstacles.remove(obs)
                    actualizar_java(session_id, lives, score)
                    
                    if lives <= 0:
                        game_over = True
                        actualizar_java(session_id, lives, score)
                    continue
                
                # Esquivado
                if obs["y"] > HEIGHT:
                    score += 10
                    obstacles.remove(obs)
                    if score % 50 == 0:
                        actualizar_java(session_id, lives, score)
        
        # Render
        screen.fill(COLOR_BG)
        
        # Dibujar jugador
        pygame.draw.rect(screen, COLOR_PLAYER, 
                        (player["x"], player["y"], player["w"], player["h"]),
                        border_radius=5)
        
        # Dibujar obstáculos
        for obs in obstacles:
            pygame.draw.rect(screen, COLOR_OBSTACLE,
                           (obs["x"], obs["y"], obs["w"], obs["h"]),
                           border_radius=3)
        
        # UI - Panel superior
        pygame.draw.rect(screen, COLOR_PANEL, (0, 0, WIDTH, 50))
        texts = [
            font.render(f"👤 {player_name}", True, COLOR_TEXT),
            font.render(f"❤️ {lives}", True, COLOR_TEXT),
            font.render(f"⭐ {score}", True, COLOR_TEXT)
        ]
        screen.blit(texts[0], (10, 12))
        screen.blit(texts[1], (160, 12))
        screen.blit(texts[2], (280, 12))
        
        # Game Over
        if game_over:
            overlay = pygame.Surface((WIDTH, HEIGHT))
            overlay.set_alpha(180)
            overlay.fill((0, 0, 0))
            screen.blit(overlay, (0, 0))
            
            go_text = font_big.render("💀 GAME OVER", True, COLOR_PLAYER)
            score_text = font.render(f"Puntaje final: {score}", True, COLOR_TEXT)
            screen.blit(go_text, (WIDTH//2 - go_text.get_width()//2, HEIGHT//2 - 30))
            screen.blit(score_text, (WIDTH//2 - score_text.get_width()//2, HEIGHT//2 + 20))
        
        pygame.display.flip()
    
    pygame.quit()
    print(f"\n👋 Sesión finalizada. Puntaje: {score}")

if __name__ == "__main__":
    main()