document.addEventListener("DOMContentLoaded", () => {
    // Referencias
    let lstProc = document.getElementById("proceso");
    let lstPerf = document.getElementById("perfil");
    let lstUser = document.getElementById("usuario");
    let txfInfo = document.getElementById("info");

    // Proceso - Elemento Seleccionado
    lstProc.addEventListener("input", () => {
        // Actualizar Campo Info
        if (lstPerf) {
            txfInfo.value = ""
                    + lstProc.options[lstProc.selectedIndex].text
                    + " - "
                    + lstPerf.options[lstPerf.selectedIndex].text;
        } else if (lstUser) {
            txfInfo.value = ""
                    + lstProc.options[lstProc.selectedIndex].text
                    + " - "
                    + lstUser.options[lstUser.selectedIndex].text;
        }
    });

    // Perfil - Elemento Seleccionado
    if (lstPerf) {
        lstPerf.addEventListener("input", () => {
            // Actualizar Campo Info
            txfInfo.value = ""
                    + lstProc.options[lstProc.selectedIndex].text
                    + " - "
                    + lstPerf.options[lstPerf.selectedIndex].text;
        });
    }

    // Usuario - Elemento Seleccionado
    if (lstUser) {
        lstUser.addEventListener("input", () => {
            // Actualizar Campo Info
            txfInfo.value = ""
                    + lstProc.options[lstProc.selectedIndex].text
                    + " - "
                    + lstUser.options[lstUser.selectedIndex].text;
        });
    }
});


