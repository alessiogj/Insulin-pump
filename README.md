## Insulin Pump Requirements

### Functional Requirements
1. **Blood Sugar Measurement and Insulin Administration**
    - Il sistema deve misurare la glicemia e somministrare insulina, se necessario, ogni 10 minuti.
    - Note: Le variazioni della glicemia sono relativamente lente, quindi una misurazione più frequente è inutile; una misurazione meno frequente potrebbe portare a livelli di zucchero inutilmente alti.

2. **Self-Test Routine**
    - Il sistema deve eseguire una routine di auto-test ogni minuto con le condizioni da testare e le azioni associate definite nella Tabella 1.
    - Note: Una routine di auto-test può scoprire problemi hardware e software e avvisare l'utente del fatto che il funzionamento normale potrebbe essere impossibile.

3. **User Registration by Medical Professionals**
    - Medico può registrare un nuovo utente da monitorare mediante la pompa di insulina inserendo i dati: Nome, Cognome, Data di nascita, Codice fiscale, Peso, Altezza, Tipologia di diabete.

4. **Parameters to Monitor**
    - Parametri da rilevare: Glicemia, pressione arteriosa, pressione venosa, BPM.

5. **Doctor's Dashboard**
    - Il medico ha a disposizione una board su cui visualizzare gli utenti, selezionare il singolo utente, aggiungere nuovi utenti, rimuovere utenti, modificare utenti.

6. **User Management and Health Monitoring**
    - La gestione dell'utente permette di monitorare il suo stato di salute, vedendo i parametri relativi alle ultime misurazioni, lo stato di salute dell'utente (in base ai log precedenti attribuisce diversi stati di salute dell'utente), possibilità di gestione manuale (il medico può decidere se far erogare al sistema l'insulina oppure gestire manualmente lo stato degli utenti, in ogni caso il medico può erogare in autonomia insulina).

7. **Alarm handling in main dashboard**
    - Il sistema deve gestire gli allarmi in modo da notificare 
      al medico, mediante la dashboard, eventuali problemi relativi ai pazienti
      (minurazioni anomale).

8. **Feedback dei medici**
   - Essendoci turni diversi, i medici possono lasciare il proprio feedback su 
     problematiche riscontrate durante il proprio turno, in modo da poter essere 
     visualizzate dai colleghi prima di procedere con la somministrazione di insulina 
     o altre operazioni.

9. **Gestione della corretta visualizzazione dei feedback**
   - I feedback dei medici devono essere visualizzati in modo da poter essere 
     gestiti in modo corretto, in modo da poter essere visualizzati in
     ordine cronologico, una volta spuntati non devono essere più visualizzati.

### Decision Table for Insulin Administration

| Condizione | Azione |
|------------|--------|
| Sugar level falling (`r2 < r1`) | `CompDose = 0` |
| Sugar level stable (`r2 = r1`) | `CompDose = 0` |
| Sugar level increasing and rate of increase decreasing `((r2 –r1) < (r1 –r0))` | `CompDose = 0` |
| Sugar level increasing and rate of increase stable or increasing `((r2 –r1) ≥ (r1 –r0))` | `CompDose = round ((r2 –r1)/4)`<br>If rounded `result = 0` then `CompDose = MinimumDose` |
