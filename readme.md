# Salon API Endpoints

## Data Import

### 1. Import CSV Data

- **Endpoint:** `/salon/importCsv`
- **Method:** `POST`
- **Request Body:** [ImportedCsvJson](#/components/schemas/ImportedCsvJson)
- **Response:** String

#### cURL Example:

```bash
curl --location 'http://localhost:8080/salon/importCsv' \
--header 'Content-Type: application/json' \
--data-raw '{
    "tableName": "CLIENTS",
    "csvData": "id,first_name,last_name,email,phone,gender,banned\ne0b8ebfc-6e57-4661-9546-328c644a3764,Dori,Dietrich,patrica@keeling.net,(272) 301-6356,Male,false\n104fdf33-c8a2-4f1c-b371-3e9c2facdfa0,Gordon,Hammes,glen@cummerata.co,403-844-1643,Male,false"
}'
```

## Appointments

### 2. Get Appointments by Client ID

- **Endpoint:** `/salon/appointments/byCliendId`
- **Method:** `GET`
- **Parameters:**
    - `cliendId` (query, required)
- **Response:** Array of [Appointment](#/components/schemas/Appointment)

### 3. Get Appointment by ID

- **Endpoint:** `/salon/appointments/{id}`
- **Method:** `GET`
- **Parameters:**
    - `id` (path, required)
- **Response:** [Appointment](#/components/schemas/Appointment)

### 4. Delete Appointment by ID

- **Endpoint:** `/salon/appointments/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - `id` (path, required)
- **Response:** String

## Clients

### 5. Get All Clients

- **Endpoint:** `/salon/clients`
- **Method:** `GET`
- **Response:** Array of [Client](#/components/schemas/Client)

### 6. Update Client

- **Endpoint:** `/salon/clients`
- **Method:** `POST`
- **Request Body:** [Client](#/components/schemas/Client)
- **Response:** [Client](#/components/schemas/Client)

#### cURL Example:

```bash
curl --location 'http://localhost:8080/salon/clients' \
--header 'Content-Type: application/json' \
--data-raw '{
  "id": "263f67fa-ce8f-447b-98cf-317656542216",
  "email": "romeorunolfon@corwin.co",
  "phone": "123456789",
  "gender": "Female",
  "banned": "false"
}'
```

### 7. Delete Client and References by ID

- **Endpoint:** `/salon/clients/clientAndReferences/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - `id` (path, required)
- **Response:** String

### 8. Get Most Loyal Clients by Date

- **Endpoint:** `/salon/clients/mostLoyal`
- **Method:** `GET`
- **Parameters:**
    - `date` (query, required, format: date)
    - `limit` (query, required, format: int32)
- **Response:** Array of [ClientLoyaltyDto](#/components/schemas/ClientLoyaltyDto)

#### cURL Example:

```bash 
curl --location 'http://localhost:8080/salon/clients/mostLoyal?date=2016-02-07&limit=50'
```

### 9. Get Client by ID

- **Endpoint:** `/salon/clients/{id}`
- **Method:** `GET`
- **Parameters:**
    - `id` (path, required)
- **Response:** [Client](#/components/schemas/Client)

### 10. Delete Client by ID

- **Endpoint:** `/salon/clients/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - `id` (path, required)
- **Response:** String

## Purchases

### 11. Get Purchase by ID

- **Endpoint:** `/salon/purchases/{id}`
- **Method:** `GET`
- **Parameters:**
    - `id` (path, required)
- **Response:** [Purchase](#/components/schemas/Purchase)

### 12. Delete Purchase by ID

- **Endpoint:** `/salon/purchases/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - `id` (path, required)
- **Response:** String

## Services

### 13. Get Service by ID

- **Endpoint:** `/salon/services/{id}`
- **Method:** `GET`
- **Parameters:**
    - `id` (path, required)
- **Response:** [Service](#/components/schemas/Service)

### 14. Delete Service by ID

- **Endpoint:** `/salon/services/{id}`
- **Method:** `DELETE`
- **Parameters:**
    - `id` (path, required)
- **Response:** String

---

*Note: For detailed schema information, please refer to the API documentation.*

