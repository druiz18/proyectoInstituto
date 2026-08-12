# Encabezado del programa
print("=" * 34)
print("          GAME HUB")
print("=" * 34)

# Solicitar información al usuario
nombre: str = input("Nombre del jugador: ")

edad: int = int(input("Edad: "))

pais: str = input("País: ")

personaje: str = input("Nombre del personaje: ")

color: str = input("Color favorito: ")

# Variables iniciales del videojuego
nivel: int = 1
vidas: int = 3
monedas: int = 100
puntos: int = 0

# Mostrar el perfil creado
print("\n========== PERFIL ==========")

print(f"Jugador: {nombre}")
print(f"Edad: {edad}")
print(f"País: {pais}")
print(f"Personaje: {personaje}")
print(f"Color favorito: {color}")
print(f"Nivel: {nivel}")
print(f"Vidas: {vidas}")
print(f"Monedas: {monedas}")
print(f"Puntos: {puntos}")