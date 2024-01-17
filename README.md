# 💉🩸Pompa di Insulina 🩸💉
> Progetto per il corso di Ingegneria del Software 
> dell'Università degli Studi di Verona.

Il sistema software è stato sviluppato per la gestione di
una pompa di insulina. Le API seguenti consentono di gestire
e monitorare vari aspetti della pompa di insulina e dei parametri
vitali del paziente.

## Scaricare il progetto
Per scaricare il progetto è necessario clonare la repository
tramite il comando `git clone https://github.com/alessiogj/Insulin-pump.git`.
Dopodiché è necessario eseguire il comando `gradle build` per
scaricare le dipendenze e compilare il progetto. Per eseguire
il progetto è necessario eseguire il comando `gradle bootRun`, 
che avvierà il server sulla porta `8080`.


## Presentazione generale
`TODO`
## Casi d'uso


## Scenari

### Scenario 1:

Livello di glucosio in diminuzione `(r2 < r1)`, il livello di glucosio è minore di `130` e maggiore di `90`

- **Assunzione iniziale**: Un utente porta con se un dispositivo che misura i parametri
vitali del paziente. Il dispositivo è in grado di misurare la pressione sanguigna,
la frequenza cardiaca, la temperatura corporea e il livello di zucchero nel sangue.
Il dispositivo è in grado di inviare i dati al server tramite una connessione internet.
La batteria del dispositivo è carica e contiene abbastanza insulita per processare eventuali
richieste. Si assume che i dati ricevuti dal dispositivo siano corretti e che il dispositivo
non invii dati errati e che il livello di insulina sia inferiore a 130.
- **Funzionamento**: Il dispositivo invia i dati al server. Il server riceve i dati e li salva
nel database. Il sistema verifica se i dati relativi al glucosio non siano fuori dai limiti
accettabili. Se il livello di glucosio sta decrementando nel tempo, l'erogazione di insulina
non avviene.
- **Cosa può andare storto**: La batteria della pompa di insulina si scarica. In questo caso 
i parametri vitali del paziente non vengono più monitorati e non è possibile erogare insulina.
In questo caso si assume che nel tempo continuino a variare, portando quindi il livello di insulina 
fuori dai limiti accettabili. In questo caso il sistema non è in grado di erogare insulina.

### Scenario 2:

Livello di glucosio stabile `(r2 = r1)`,il livello di glucosio è minore di `130` e maggiore di `90`

- **Assunzione iniziale**: Un utente porta con se un dispositivo che misura i parametri
  vitali del paziente. Il dispositivo è in grado di misurare la pressione sanguigna,
  la frequenza cardiaca, la temperatura corporea e il livello di zucchero nel sangue.
  Il dispositivo è in grado di inviare i dati al server tramite una connessione internet.
  La batteria del dispositivo è carica e contiene abbastanza insulita per processare eventuali
  richieste. Si assume che i dati ricevuti dal dispositivo siano corretti e che il dispositivo
  non invii dati errati e che il livello di insulina sia inferiore a 130.
- **Funzionamento**: Il dispositivo invia i dati al server. Il server riceve i dati e li salva
  nel database. Il sistema verifica se i dati relativi al glucosio non siano fuori dai limiti
  accettabili. Se il livello di glucosio è stabile nel tempo, l'erogazione di insulina
  non avviene.
- **Cosa può andare storto**: La batteria della pompa di insulina si scarica. In questo caso
  i parametri vitali del paziente non vengono più monitorati e non è possibile erogare insulina.
  In questo caso si assume che nel tempo continuino a variare, portando quindi il livello di insulina
  fuori dai limiti accettabili. In questo caso il sistema non è in grado di erogare insulina.

### Scenario 3:

Livello di glucosio in aumento `((r2 – r1) ≥ (r1 – r0))`, il risultato è diverso da `0`, il livello
di glucosio è minore di `130` e maggiore di `90`
- **Assunzione iniziale**: Un utente porta con se un dispositivo che misura i parametri
  vitali del paziente. Il dispositivo è in grado di misurare la pressione sanguigna,
  la frequenza cardiaca, la temperatura corporea e il livello di zucchero nel sangue.
  Il dispositivo è in grado di inviare i dati al server tramite una connessione internet.
  La batteria del dispositivo è carica e contiene abbastanza insulita per processare eventuali
  richieste. Si assume che i dati ricevuti dal dispositivo siano corretti e che il dispositivo
  non invii dati errati e che il livello di insulina sia inferiore a 130.
- **Funzionamento**: Il dispositivo invia i dati al server. Il server riceve i dati e li salva
  nel database. Il sistema verifica se i dati relativi al glucosio non siano fuori dai limiti
  accettabili. Se il livello di glucosio è in aumento nel tempo, l'erogazione di insulina
  avviene per abbassare il livello di glucosio del paziente e portarlo entro i limiti accettabili
  secondo il seguente calcolo:
  - `se l'ultima misurazione - prima misurazione è maggiore o uguale alla differenza tra la
    prima e la seconda misurazione`
  - `La dose di compnsazione è calcolata come la differenza tra la prima e la seconda misurazione diviso 4`
  - `Se la dose di compensazione è un valore minore di 1 e maggiore di 0, la dose di compensazione è 1`
  - `Se la dose di compensazione è un valore maggiore di 1, la dose di compensazione è il valore arrotondato
    all'intero più vicino`

### Scenario 4:

Livello di glucosio in aumento `((r2 – r1) ≥ (r1 – r0))`, il risultato è uguale da `0` e il livello
di glucosio è minore di `130` e maggiore di `90`
- **Assunzione iniziale**: Un utente porta con se un dispositivo che misura i parametri
  vitali del paziente. Il dispositivo è in grado di misurare la pressione sanguigna,
  la frequenza cardiaca, la temperatura corporea e il livello di zucchero nel sangue.
  Il dispositivo è in grado di inviare i dati al server tramite una connessione internet.
  La batteria del dispositivo è carica e contiene abbastanza insulita per processare eventuali
  richieste. Si assume che i dati ricevuti dal dispositivo siano corretti e che il dispositivo
  non invii dati errati e che il livello di insulina sia inferiore a 130.
- **Funzionamento**: Il dispositivo invia i dati al server. Il server riceve i dati e li salva
  nel database. Il sistema verifica se i dati relativi al glucosio non siano fuori dai limiti
  accettabili. Se il livello di glucosio è in aumento nel tempo, l'erogazione di insulina
  avviene per abbassare il livello di glucosio del paziente e portarlo entro i limiti accettabili
  secondo il seguente calcolo:
  - `se l'ultima misurazione - prima misurazione è maggiore o uguale alla differenza tra la
    prima e la seconda misurazione`
  - `La dose di compnsazione è calcolata come la differenza tra la prima e la seconda misurazione diviso 4`
  - `Se la dose di compensazione è 0, la dose di compensazione sarà la dose minima, ovvero 1`

### Scenario 5:

Livello di glucosio fuori dai limiti accettabili `livello glucosio ≥ 130`:

- **Assunzione iniziale**: Un utente porta con se un dispositivo che misura i parametri
  vitali del paziente. Il dispositivo è in grado di misurare la pressione sanguigna,
  la frequenza cardiaca, la temperatura corporea e il livello di zucchero nel sangue.
  Il dispositivo è in grado di inviare i dati al server tramite una connessione internet.
  La batteria del dispositivo è carica e contiene abbastanza insulita per processare eventuali
  richieste. Si assume che i dati ricevuti dal dispositivo siano corretti e che il dispositivo
  non invii dati errati e che il livello di insulina sia maggiore o uguale a 130.
- **Funzionamento**: Il dispositivo invia i dati al server. Il server riceve i dati e li salva
  nel database. Il sistema verifica se i dati relativi al glucosio non siano fuori dai limiti
  accettabili. Se il livello di glucosio è fuori dai limiti accettabili, in qualunque situazione,
  la pompa erogherà insulina per abbassare il livello di glucosio del paziente e portarlo entro
  i limiti accettabili.
- **Cosa può andare storto**: La batteria della pompa di insulina si scarica. In questo caso
    i parametri vitali del paziente non vengono più monitorati e non è possibile erogare insulina.
    In questo caso si assume che nel tempo continuino a variare, portando quindi il livello di insulina
    fuori dai limiti accettabili. In questo caso il sistema non è in grado di erogare insulina.

### Scenario 7:

- niente batteria

### Scenario 8:

- niente insulina

### Scenario 9:

- visualizzazione grafici real time

### Scenario 10:

-visualizzazione storico dei dati (grafici)
 con scelta delle date


## Documentazione API relative ai sensori

### 1. Cambio della batteria del pompa di insulina
- **Descrizione**: Sostituisce la batteria della pompa di insulina.
- **URL**: `/sensors/battery/replace`
- **Metodo**: `PUT`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: `200 OK` se la sostituzione è avvenuta con successo

### 2. Ricarica del serbatoio del pompa di insulina
- **Descrizione**: Ricarica il serbatoio della pompa di insulina.
- **URL**: `/sensors/tank/refill`
- **Metodo**: `PUT`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: `200 OK` se la ricarica è avvenuta con successo

### 3. Stato dei sensori
- **Descrizione**: Restituisce lo stato dei sensori della pompa di insulina.
- **URL**: `/sensors/status`
- **Metodo**: `GET`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: Ogggetto `SensorStatusDto` con i dettagli dello stato dei sensori

Schema JSON dell'oggetto `SensorStatusDto`:
```json
{
  "battery" : 100,
  "tank"    : 100
}
``` 
## Documentazione API Parametri Vitali

### 1. Ricerca Parametri Vitali per Intervallo Temporale
- **Descrizione**: Restituisce i parametri vitali del paziente per un determinato intervallo temporale.
- **URL**: `/vitalparameters/date`
- **Metodo**: `GET`
- **Corpo della Richiesta**: Oggetto `DateIntervalDto` con date di inizio e fine.
- **Risposta**: `Iterable<VitalParametersDto>` con i parametri vitali.

Schema `JSON` per la richiesta di ricerca per rntervallo
```json
{
  "startDate" : "2021-01-01T00:00:00",
  "endDate"   : "2021-01-01T00:00:00"
}
```

Schema `JSON` per la risposta di ricerca per intervallo temporale
```json
[
  {
    "timestamp": "2023-01-06T14:07:11.530341",
    "bloodPressureSystolic": 120,
    "bloodPressureDiastolic": 80,
    "heartRate": 70,
    "bloodSugarLevel": 100,
    "temperature": 36.5
  },
  {
    "timestamp": "2023-01-06T14:07:11.530341",
    "bloodPressureSystolic": 110,
    "bloodPressureDiastolic": 70,
    "heartRate": 80,
    "bloodSugarLevel": 100,
    "temperature": 36.5
  }
]
```

### 2. Recuperare tutti i parametri vitali 
- **Descrizione**: Restituisce tutti i parametri vitali del paziente.
- **URL**: `/vitalparameters`
- **Metodo**: `GET`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: `Iterable<VitalParametersDto>` con i parametri vitali.

Schema `JSON` per la risposta di ricerca per intervallo temporale
```json
[
  {
    "timestamp": "2023-01-06T14:07:11.530341",
    "bloodPressureSystolic": 120,
    "bloodPressureDiastolic": 80,
    "heartRate": 70,
    "bloodSugarLevel": 100,
    "temperature": 36.5
  },
  {
    "timestamp": "2023-01-06T14:07:11.530341",
    "bloodPressureSystolic": 110,
    "bloodPressureDiastolic": 70,
    "heartRate": 80,
    "bloodSugarLevel": 100,
    "temperature": 36.5
  }
]
```

### 3. Recupero dell'ultimo parametro vitale
- **Descrizione**: Restituisce l'ultimo parametro vitale del paziente.
- **URL**: `/vitalparameters/last`
- **Metodo**: `GET`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: `VitalParametersDto` con l'ultimo parametro vitale.
- **Errore**: `404 Not Found` se non sono presenti parametri vitali.

Schema `JSON` per la risposta di ricerca per intervallo temporale
```json
{
  "timestamp": "2023-01-06T14:07:11.530341",
  "bloodPressureSystolic": 120,
  "bloodPressureDiastolic": 80,
  "heartRate": 70,
  "bloodSugarLevel": 100,
  "temperature": 36.5
}
```

### 4. Eliminazione di tutte le misurazioni precedenti 
- **Descrizione**: Elimina tutte le misurazioni precedenti.
- **URL**: `/vitalparameters`
- **Metodo**: `DELETE`
- **Corpo della Richiesta**: Vuoto
- **Risposta**: `204 No Content` se l'eliminazione è avvenuta con successo.

## Test
`TODO`

## Autori
- [Alessio Gjergji](https://github.com/alessiogj)
- [Nicolò Piccoli](https://github.com/nickkpiccoli)
