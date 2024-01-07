# Pompa di Insulina 💉

Il progetto è stato sviluppato per il corso di Ingegneria
e scienze informatiche dell'Università di Verona. 
Il progetto consiste nella realizzazione di un sistema
software per la gestione di una pompa di insulina.

## Documentazione API Parametri Vitali

### 1. Ricerca Parametri Vitali per Intervallo Temporale

- **Descrizione**: Recupera i parametri vitali di un paziente per un intervallo temporale specificato.
- **URL**: `vitalparameters/date`
- **Metodo**: `POST`
- **Corpo della Richiesta**:
    - Oggetto `DateIntervalDto` contenente le date di inizio e fine dell'intervallo.
- **Risposta**:
    - `Iterable` di oggetti `VitalParametersDto` che rappresentano i parametri vitali nell'intervallo specificato.

#### Schema JSON per la richiesta di ricerca per intervallo temporale

```json
{
  "startDate": "2023-01-06T14:07:11.530341",
    "endDate": "2024-01-06T14:07:11.530341"
}
```

#### Schema JSON per la risposta di ricerca per intervallo temporale

```json
[
  {
    "id": 1,
    "patientId": 1,
    "date": "2023-01-06T14:07:11.530341",
    "bloodPressure": 120,
    "heartRate": 80,
    "temperature": 36.5,
    "bloodOxygenLevel": 98
  },
  {
    "id": 2,
    "patientId": 1,
    "date": "2023-01-06T14:07:11.530341",
    "bloodPressure": 120,
    "heartRate": 80,
    "temperature": 36.5,
    "bloodOxygenLevel": 98
  }
]
```

### 2. Recupero di tutti i Parametri Vitali

- **Descrizione**: Recupera tutti i parametri vitali di un paziente.
- **URL**: `vitalparameters/`
- **Metodo**: `GET`
- **Corpo della Richiesta**:
  - Il corpo della richiesta è vuoto.
- **Risposta**:
    - `Iterable` di oggetti `VitalParametersDto` che rappresentano i parametri vitali del paziente.

#### Schema JSON per la risposta di recupero di tutti i parametri vitali

```json
[
  {
    "id": 1,
    "patientId": 1,
    "date": "2023-01-06T14:07:11.530341",
    "bloodPressure": 120,
    "heartRate": 80,
    "temperature": 36.5,
    "bloodOxygenLevel": 98
  },
  {
    "id": 2,
    "patientId": 1,
    "date": "2023-01-06T14:07:11.530341",
    "bloodPressure": 120,
    "heartRate": 80,
    "temperature": 36.5,
    "bloodOxygenLevel": 98
  }
]
```

### 3. Recupero dell'ultimo Parametro Vitale misurato per un paziente

- **Descrizione**: Recupera l'ultimo parametro vitale misurato per un paziente.
- **URL**: `vitalparameters/last`
- **Metodo**: `GET`
- **Corpo della Richiesta**:
    - Il corpo della richiesta è vuoto.
- **Risposta**:
    - Oggetto `VitalParametersDto` che rappresenta l'ultimo parametro vitale misurato per il paziente.
    - `404 Not Found` se il paziente non ha ancora misurato alcun parametro vitale.

#### Schema JSON per la risposta di recupero dell'ultimo parametro vitale

```json
{
  "id": 1,
  "patientId": 1,
  "date": "2023-01-06T14:07:11.530341",
  "bloodPressure": 120,
  "heartRate": 80,
  "temperature": 36.5,
  "bloodOxygenLevel": 98
}
```

### 4. Recupero delle informazioni sui sensori

- **Descrizione**: Recupera le informazioni sui sensori.
- **URL**: `sensors/status`
- **Metodo**: `GET`
- **Corpo della Richiesta**:
    - Il corpo della richiesta è vuoto.
- **Risposta**:
    - Oggetto `SensorStatusDto` che rappresenta lo stato dei sensori.

#### Schema JSON per la risposta di recupero delle informazioni sui sensori
    
```json
{
  "battery": 100,
  "tank": true
}
```

## Documentazione API relative ai Sensori 

### 1. Rimpiazzo della batteria

- **Descrizione**: Rimpiazzo della batteria.
- **URL**: `sensors/battery/replace`
- **Metodo**: `PUT`
- **Corpo della Richiesta**:
    - Il corpo della richiesta è vuoto.
- **Risposta**:
  - `200 OK` se il rimpiazzo della batteria è andato a buon fine.

### 2. Ricarica del serbatoio

- **Descrizione**: Ricarica del serbatoio.
- **URL**: `sensors/tank/refill`
- **Metodo**: `PUT`
- **Corpo della Richiesta**:
    - Il corpo della richiesta è vuoto.
- **Risposta**:
  - `200 OK` se la ricarica del serbatoio è andata a buon fine.