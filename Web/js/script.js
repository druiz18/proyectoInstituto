// OBTENER ELEMENTOS DEL DOM

const formulario = document.querySelector("#formulario");

const nombre = document.querySelector("#nombre");

const correo = document.querySelector("#correo");

const edad = document.querySelector("#edad");

const password = document.querySelector("#password");

const confirmarPassword = document.querySelector("#confirmarPassword");

const terminos = document.querySelector("#terminos");

const mensajeFormulario = document.querySelector("#mensajeFormulario");

// MENSAJES DE ERROR

const errorNombre = document.querySelector("#errorNombre");

const errorCorreo = document.querySelector("#errorCorreo");

const errorEdad = document.querySelector("#errorEdad");

const errorPassword = document.querySelector("#errorPassword");

const errorConfirmarPassword = document.querySelector(
  "#errorConfirmarPassword",
);

const errorTerminos = document.querySelector("#errorTerminos");

// FUNCIÓN PARA MARCAR UN CAMPO COMO INVÁLIDO

function marcarInvalido(campo, mensajeElemento, mensaje) {
  campo.classList.remove("campo-valido");

  campo.classList.add("campo-invalido");

  mensajeElemento.textContent = mensaje;
}

// FUNCIÓN PARA MARCAR UN CAMPO COMO VÁLIDO

function marcarValido(campo, mensajeElemento) {
  campo.classList.remove("campo-invalido");

  campo.classList.add("campo-valido");

  mensajeElemento.textContent = "";
}

// VALIDAR NOMBRE

function validarNombre() {
  const valor = nombre.value.trim();

  if (valor === "") {
    marcarInvalido(nombre, errorNombre, "El nombre es obligatorio.");

    return false;
  }

  if (valor.length < 3) {
    marcarInvalido(
      nombre,
      errorNombre,
      "El nombre debe tener mínimo 3 caracteres.",
    );

    return false;
  }

  marcarValido(nombre, errorNombre);

  return true;
}

// VALIDAR CORREO

function validarCorreo() {
  const valor = correo.value.trim();

  if (valor === "") {
    marcarInvalido(correo, errorCorreo, "El correo es obligatorio.");

    return false;
  }

  const patronCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  if (!patronCorreo.test(valor)) {
    marcarInvalido(
      correo,
      errorCorreo,
      "Ingrese un correo electrónico válido.",
    );

    return false;
  }

  marcarValido(correo, errorCorreo);

  return true;
}

// VALIDAR EDAD

function validarEdad() {
  const valor = edad.value.trim();

  if (valor === "") {
    marcarInvalido(edad, errorEdad, "La edad es obligatoria.");

    return false;
  }

  const edadNumero = Number(valor);

  if (edadNumero < 18 || edadNumero > 100) {
    marcarInvalido(edad, errorEdad, "La edad debe estar entre 18 y 100 años.");

    return false;
  }

  marcarValido(edad, errorEdad);

  return true;
}

// VALIDAR CONTRASEÑA

function validarPassword() {
  const valor = password.value;

  if (valor === "") {
    marcarInvalido(password, errorPassword, "La contraseña es obligatoria.");

    return false;
  }

  if (valor.length < 8) {
    marcarInvalido(
      password,
      errorPassword,
      "La contraseña debe tener mínimo 8 caracteres.",
    );

    return false;
  }

  const contieneNumero = /\d/.test(valor);

  if (!contieneNumero) {
    marcarInvalido(
      password,
      errorPassword,
      "La contraseña debe contener al menos un número.",
    );

    return false;
  }

  marcarValido(password, errorPassword);

  return true;
}

// VALIDAR CONFIRMACIÓN DE CONTRASEÑA

function validarConfirmarPassword() {
  const valor = confirmarPassword.value;

  if (valor === "") {
    marcarInvalido(
      confirmarPassword,
      errorConfirmarPassword,
      "Debe confirmar la contraseña.",
    );

    return false;
  }

  if (valor !== password.value) {
    marcarInvalido(
      confirmarPassword,
      errorConfirmarPassword,
      "Las contraseñas no coinciden.",
    );

    return false;
  }

  marcarValido(confirmarPassword, errorConfirmarPassword);

  return true;
}

// VALIDAR TÉRMINOS

function validarTerminos() {
  if (!terminos.checked) {
    errorTerminos.textContent = "Debe aceptar los términos y condiciones.";

    return false;
  }

  errorTerminos.textContent = "";

  return true;
}

// EVENTO SUBMIT

formulario.addEventListener("submit", function (event) {
  // Evitar el envío automático
  event.preventDefault();

  // Ejecutar todas las validaciones

  const nombreValido = validarNombre();

  const correoValido = validarCorreo();

  const edadValida = validarEdad();

  const passwordValida = validarPassword();

  const confirmarPasswordValida = validarConfirmarPassword();

  const terminosValidos = validarTerminos();

  // Comprobar resultado

  const formularioValido =
    nombreValido &&
    correoValido &&
    edadValida &&
    passwordValida &&
    confirmarPasswordValida &&
    terminosValidos;

  if (formularioValido) {
    mensajeFormulario.textContent = "¡Registro realizado correctamente!";

    mensajeFormulario.classList.remove("error");

    mensajeFormulario.classList.add("exito");
  } else {
    mensajeFormulario.textContent =
      "Por favor, corrija los errores del formulario.";

    mensajeFormulario.classList.remove("exito");

    mensajeFormulario.classList.add("error");
  }
});

// VALIDACIÓN EN TIEMPO REAL

nombre.addEventListener("input", validarNombre);

correo.addEventListener("input", validarCorreo);

edad.addEventListener("input", validarEdad);

password.addEventListener("input", function () {
  validarPassword();

  // Si el usuario modifica nuevamente
  // la contraseña, debemos comprobar
  // otra vez su confirmación.

  if (confirmarPassword.value !== "") {
    validarConfirmarPassword();
  }
});

confirmarPassword.addEventListener("input", validarConfirmarPassword);

terminos.addEventListener("change", validarTerminos);
