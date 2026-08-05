print("=" * 35)
print("          GAME HUB")
print("=" * 35)

nombre: str = input("Nombre del jugador: ")
edad: int = int(input("Edad: "))
pais: str = input("País: ")
personaje: str = input("Nombre del personaje: ")
color: str = input("Color favorito: ")

vidas: int = 3
nivel: int = 1
monedas: int = 100
puntos: int = 0

print("\n==============================")
print("PERFIL DEL JUGADOR")
print("==============================")

print(f"Jugador   : {nombre}")
print(f"Edad      : {edad}")
print(f"País      : {pais}")
print(f"Personaje : {personaje}")
print(f"Color     : {color}")
print(f"Nivel     : {nivel}")
print(f"Vidas     : {vidas}")
print(f"Monedas   : {monedas}")
print(f"Puntos    : {puntos}")