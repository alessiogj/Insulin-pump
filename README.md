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

## Test API relative ai Parametri Vitali
### Ambiente di Test
- **Framework**: Spring Boot Test
- **Test Runner**: SpringRunner
- **Strumento di Mocking**: RestAssured per la simulazione delle richieste HTTP

### Metodi di Test

#### 1. `testGetVitalParameters`
- **Descrizione**: Testa il recupero di un elenco vuoto di parametri vitali.
- **Aspettativa**: Il sistema dovrebbe restituire un codice di stato 200 con un corpo della risposta vuoto.

#### 2. `testGetVitalParametersNotEmpty`
- **Descrizione**: Verifica la funzionalità di recupero di un elenco non vuoto di parametri vitali.
- **Configurazione**: Aggiunge due set di parametri vitali al repository.
- **Aspettativa**: L'API dovrebbe restituire un codice di stato 200 e un elenco non vuoto di parametri vitali.

#### 3. `testGetLastVitalParameter`
- **Descrizione**: Testa il recupero dell'ultimo parametro vitale registrato.
- **Configurazione**: Inserisce due diversi set di parametri vitali.
- **Aspettativa**: L'API dovrebbe restituire l'ultimo set di parametri vitali con un codice di stato 200.

#### 4. `testGetVitalParametersWithDateInterval`
- **Descrizione**: Valida il recupero dei parametri vitali entro un intervallo di date specificato.
- **Configurazione**: Aggiunge parametri vitali e testa con due diversi intervalli di date - uno all'interno e uno al di fuori dell'intervallo dei dati inseriti.
- **Aspettativa**: Per l'intervallo di date incluso, l'API dovrebbe restituire una risposta non vuota; per l'intervallo di date non incluso, ci si aspetta una risposta vuota.

#### 5. `testGetVitalParametersWithInvalidDateInterval`
- **Descrizione**: Testa il comportamento quando viene fornito un intervallo di date non valido.
- **Aspettativa**: L'API dovrebbe restituire un codice di stato 400, indicando una richiesta errata a causa dell'intervallo di date non valido.

## Test API relative ai Sensori
### Ambiente di Test
- **Framework**: Spring Boot Test con RestAssured per le richieste HTTP
- **Test Runner**: SpringRunner
- **Base URI**: Impostata su `http://localhost:8080` per le richieste API.

### Metodi di Test

#### 1. `testReplaceBattery`
- **Descrizione**: Testa la funzionalità di sostituzione della batteria del sensore.
- **Metodo API**: PUT `/sensors/battery/replace`
- **Aspettativa**: L'API dovrebbe restituire un codice di stato 200, indicando che la batteria è stata sostituita con successo.

#### 2. `testRefillInsulinPump`
- **Descrizione**: Verifica la funzionalità di ricarica del serbatoio della pompa di insulina.
- **Metodo API**: PUT `/sensors/tank/refill`
- **Aspettativa**: L'API dovrebbe restituire un codice di stato 200, confermando il successo del riempimento del serbatoio.

#### 3. `testGetStatus`
- **Descrizione**: Testa la funzionalità di ottenimento dello stato corrente dei sensori.
- **Metodo API**: GET `/sensors/status`
- **Aspettativa**: L'API dovrebbe restituire un codice di stato 200, fornendo lo stato attuale dei sensori.

## Test dei MOCK 
### Ambiente di Test
- **Framework**: Spring Boot Test con Mockito per il mocking degli oggetti
- **Test Runner**: SpringRunner
- **Ambiente**: Test integrati senza avviare il server web

### Metodi di Test

#### 1. `testDecrBattery`
- **Descrizione**: Testa la diminuzione della capacità della batteria quando non è scarica.
- **Aspettativa**: Verifica che la batteria venga scaricata.

#### 2. `testDecrBatteryBatteryLow`
- **Descrizione**: Testa che la batteria non venga scaricata quando è già scarica.
- **Aspettativa**: Verifica che la batteria non venga ulteriormente scaricata.

#### 3. `testModifyVitalParameters`
- **Descrizione**: Testa la modifica dei parametri vitali del paziente.
- **Aspettativa**: Verifica che vengano modificati la pressione, il livello di glucosio e la temperatura del paziente.

#### 4. `testNewVitalSignsBatteryLow`
- **Descrizione**: Testa che i nuovi segni vitali non vengano salvati se la batteria è scarica.
- **Aspettativa**: Verifica che i segni vitali non vengano salvati.

#### 5. `testNewVitalSignsNormalConditions`
- **Descrizione**: Testa il salvataggio dei nuovi segni vitali in condizioni normali.
- **Aspettativa**: Verifica che i segni vitali vengano salvati correttamente.

#### 6. `testInsulinInjectionWhenGlucoseLevelHigh`
- **Descrizione**: Testa l'iniezione di insulina quando il livello di glucosio è alto.
- **Aspettativa**: Verifica che l'insulina venga iniettata.

#### 7. `testInsulinInjectionWhenGlucoseLevelLow`
- **Descrizione**: Testa che l'insulina non venga iniettata quando il livello di glucosio è basso.
- **Aspettativa**: Verifica che l'insulina non venga iniettata.

#### 8. `testInsulinInjectionWhenInsulinTankEmpty`
- **Descrizione**: Testa che l'insulina non venga iniettata quando il serbatoio è vuoto.
- **Aspettativa**: Verifica che l'insulina non venga iniettata.

#### 9. `testInsulinInjectionWhenBatteryLow`
- **Descrizione**: Testa che l'insulina non venga iniettata quando la batteria è scarica.
- **Aspettativa**: Verifica che l'insulina non venga iniettata.
