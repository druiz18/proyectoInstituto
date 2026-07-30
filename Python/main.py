# ======================================================
# ACTIVIDAD 2 - Declaración de variables
# ======================================================

print("======================================")
print("ACTIVIDAD 2 - DECLARACIÓN DE VARIABLES")
print("======================================")

nombre: str = "María Pérez"
edad: int = 20
estatura: float = 1.65
peso: float = 58.5
ciudad: str = "Bogotá"
es_estudiante: bool = True

print(f"Nombre: {nombre}")
print(f"Edad: {edad} años")
print(f"Estatura: {estatura} m")
print(f"Peso: {peso} kg")
print(f"Ciudad: {ciudad}")
print(f"¿Es estudiante?: {es_estudiante}")

# ======================================================
# ACTIVIDAD 3 - Entrada y salida de datos
# ======================================================

print("\n======================================")
print("ACTIVIDAD 3 - ENTRADA Y SALIDA DE DATOS")
print("======================================")

nombre_completo: str = input("Ingrese su nombre completo: ")
edad_usuario: int = int(input("Ingrese su edad: "))
ciudad_residencia: str = input("Ingrese su ciudad de residencia: ")
programa: str = input("Ingrese su programa académico: ")

print()

print(
    f"Hola {nombre_completo}, tienes {edad_usuario} años, "
    f"vives en {ciudad_residencia} y estudias {programa}."
)