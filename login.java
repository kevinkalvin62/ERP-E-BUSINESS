// Elias lo que está en verde es la documentación de que hace cada parte del código, traté de simplificarlo, cuialquier cosa mándame mensaje
// Este código se ejecuta cuando el documento HTML está completamente cargado
$(document).ready(function() {
    // Selecciona el formulario de inicio de sesión y el mensaje de error
    const $formulario = $('#inicio_de_sesion');
    const $mensajeerror = $('#mensaje_de_error');
    let intentosfallidos = 0; // Contador de intentos fallidos, se irá incrementando dependiendo del usuario
    let intentosmaximos = 3; // Número máximo de intentos permitidos, de lo contrario se irá a volar

    $formulario.on('submit', function(evento) { // Esto actua al momento que se envia la solicitud de enviar datos del formulario anteriormente lleno
        evento.preventDefault(); // Evita que el formulario se envíe de la manera tradicional, así que no tiene que recargar la página por asi decirlo

        // Obtiene los valores de los campos de usuario y contraseña o las cajitas de texto, como les digas
        const usuario = $('#usuario').val();
        const contrasena = $('#clave').val();

        // Crea un objeto con los datos del formulario, empaquetando todo en uno
        const datos = {
            usuario: usuario,
            contrasena: contrasena
        };

        // Convierte el objeto en una cadena JSON, que es lo que ocupamos para que el servidor reciba las instrucciones necesarias
        const datosJSON = JSON.stringify(datos);

        // Verifica si el usuario y la contraseña son correctos, obvio esto lo compara con los usuarios y contraseñas que ya existen
        if (usuario === "Pedrito123" && contrasena === "Contra123") { // Este es un ejemplo de lo que contendría un usuario y su contraseña que ya están en una base de datos
            window.location = "login.html"; // Lleva al usuario a otra página, osea cuando acceda, si y SOLO SI los valores de usuario y contraseña son correctos con lo wue tenemos dado de alta en nuestra base de datos
        } else {
            intentosfallidos++; // Incrementa el contador de intentos fallidos, como tenemos condición con un máximo, si se supera los mandmaos a volar
            if (intentosfallidos >= intentosmaximos) {
                alert("Demasiados intentos fallidos. La página se bloqueará."); // Para que sea seguro, se deshabilitarán todos los campos de entrada y el botón que damos click para iniciar sesión
                $formulario.find('input').prop('disabled', true);
                $formulario.find('button[type="submit"]').prop('disabled', true);
            } else {
                alert(`Datos incorrectos, verifique usuario y contraseña, intentos restantes: ${intentosmaximos - intentosfallidos}`);
            }
        }

        $.ajax({ // Esta parte chambea y envia datos JSON al servidor, aunque lo que se llama AJAX ayuda a que no se reinicie la página, es innecesario
            url: 'https://servidorpendiente/api/login', // URL o link como le digas, del servidor (todavía no está creado) al que se enviarán los datos
            method: 'POST', // Es un método HTTP utilizado para enviar los datos desde un formulario web para que se procese la info
            contentType: 'application/json', // Tipo de contenido de los datos enviados, porque ya establecimos que JSON para el servidor
            data: datosJSON, // Datos JSON que se enviarán al servidor
            success: function(data) {
                console.log('Envío exitoso:', data); // Muestra un mensaje que confirme que la conexión está hecha
            },
            error: function(error) {
                console.error('Envío erróneo:', error); // Muestra un mensaje de notificación de error en caso de que la solicitud falle
            }
        });
        console.log(datosJSON); // Muestra la cadena JSON en la consola para verificarse
    });
});