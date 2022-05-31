/* global URL */

document.addEventListener("DOMContentLoaded", () => {
    // Referencias
    let selAvatar = document.getElementById("avatar");
    let imgAvatar = document.querySelector(".avatar-frame .actual");
    let divAvatar = document.querySelector(".avatar-name");
    let imgBackup = document.querySelector(".avatar-frame .backup");
    let btnReset = document.querySelector(".btn-reset");

    // Listener Change
    selAvatar.addEventListener("change", () => {
        // Archivo/s seleccionado/s > Array
        let archivos = selAvatar.files;

        // Si no hay archivos salimos de la función y quitamos la imagen
        if (archivos && archivos.length) {
            // Primer archivo > Objeto ObjectURL
            let object = URL.createObjectURL(archivos[0]);

            // Objeto ObjectURL > IMG SRC
            imgAvatar.src = object;

            // IMG SRC > IMG Name
            divAvatar.innerText = archivos[0].name.replace(/\..*/, "");
        }
    });

    // Listener Change
    btnReset.addEventListener("click", () => {
        imgAvatar.src = imgBackup.src;
        divAvatar.innerText = "Predeterminado";
    });
});
