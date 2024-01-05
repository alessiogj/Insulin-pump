document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Previene il comportamento standard del modulo

    var data = {
        name: this['name'].value,
        surname: this['surname'].value,
        fiscalCode: this['fiscal'].value,
        birthDate: this['birthdate'].value,
        height: this['height'].value,
        weight: this['weight'].value
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/patient/", true);
    xhr.setRequestHeader("Content-type", "application/json");

    xhr.onload = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("Risposta ricevuta: ", xhr.responseText);
            // Gestisci qui la risposta
        } else {
            console.error("Errore nella richiesta: ", xhr.responseText);
        }
    };

    xhr.send(JSON.stringify(data));
});