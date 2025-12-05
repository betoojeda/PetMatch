# Documentación de la API de PetMatch

Esta es la documentación oficial para la API del backend de PetMatch.

## Base URL

Todos los endpoints de la API están prefijados con `/api`.

---

## 1. Autenticación (`/api/auth`)

Endpoints para el registro y la autenticación de usuarios.

### 1.1. Registrar un Nuevo Usuario

- **Endpoint:** `POST /api/auth/register`
- **Descripción:** Crea un nuevo usuario en el sistema.
- **Autenticación:** No requerida.
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "name": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "gender": "Masculino",
    "numberOfPets": 1,
    "profileDescription": "Amante de los perros y la naturaleza."
  }
  ```
- **Respuesta Exitosa (200 OK):**
  ```json
  {
    "token": "jwt.token.string",
    "id": 1,
    "name": "John",
    "email": "john.doe@example.com",
    "role": "USER"
  }
  ```
- **Respuestas de Error:**
  - `400 Bad Request`: Si el correo electrónico ya está registrado. El cuerpo de la respuesta contendrá un mensaje de error.

### 1.2. Iniciar Sesión

- **Endpoint:** `POST /api/auth/login`
- **Descripción:** Autentica a un usuario y devuelve un token JWT.
- **Autenticación:** No requerida.
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "email": "john.doe@example.com",
    "password": "password123"
  }
  ```
- **Respuesta Exitosa (200 OK):**
  ```json
  {
    "token": "jwt.token.string",
    "id": 1,
    "name": "John",
    "email": "john.doe@example.com",
    "role": "USER"
  }
  ```
- **Respuestas de Error:**
  - `401 Unauthorized`: Si las credenciales son incorrectas.

### 1.3. Solicitar Reseteo de Contraseña

- **Endpoint:** `POST /api/auth/forgot-password`
- **Descripción:** Inicia el proceso de recuperación de contraseña. Genera un token de un solo uso y lo envía al correo del usuario.
- **Autenticación:** No requerida.
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "email": "john.doe@example.com"
  }
  ```
- **Respuesta Exitosa (200 OK):** Sin contenido. La respuesta es siempre exitosa para no revelar si un correo existe o no en la base de datos.

### 1.4. Resetear la Contraseña

- **Endpoint:** `POST /api/auth/reset-password`
- **Descripción:** Actualiza la contraseña del usuario utilizando un token válido.
- **Autenticación:** No requerida.
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "token": "el-token-recibido-por-correo",
    "newPassword": "nuevaContraseñaSegura123"
  }
  ```
- **Respuesta Exitosa (200 OK):** Sin contenido.
- **Respuestas de Error:**
  - `400 Bad Request`: Si el token es inválido o ha expirado.

---

## 2. Mascotas (`/api/pets`)

Endpoints para la gestión de mascotas.

### 2.1. Crear una Nueva Mascota

- **Endpoint:** `POST /api/pets`
- **Descripción:** Añade una nueva mascota al perfil de un usuario.
- **Autenticación:** Requerida (`Bearer Token`). El usuario debe estar autenticado.
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "name": "Fido",
    "type": "Perro",
    "breed": "Labrador",
    "age": 3,
    "description": "Juguetón y amigable.",
    "photoUrl": "http://example.com/fido.jpg",
    "size": "Mediano",
    "gender": "Macho",
    "energyLevel": "Alto",
    "temperament": "Sociable",
    "compatibleWithDogs": true,
    "compatibleWithCats": false,
    "compatibleWithChildren": true,
    "specialNeeds": "Ninguna",
    "trainingLevel": "Básico",
    "vaccinated": true,
    "dewormed": true,
    "sterilized": true,
    "history": "Rescatado de un refugio.",
    "ownerId": 1
  }
  ```
- **Respuesta Exitosa (200 OK):** Devuelve el objeto `PetDto` de la mascota creada.

### 2.2. Actualizar una Mascota

- **Endpoint:** `PUT /api/pets/{id}`
- **Descripción:** Actualiza los detalles de una mascota existente.
- **Autenticación:** Requerida (`Bearer Token`). Solo el dueño de la mascota o un administrador pueden realizar esta acción.
- **Parámetros de Ruta:**
  - `id` (Long): El ID de la mascota a actualizar.
- **Cuerpo de la Solicitud (`application/json`):** El objeto `PetDto` con los campos a actualizar.
- **Respuesta Exitosa (200 OK):** Devuelve el objeto `PetDto` de la mascota actualizada.
- **Respuestas de Error:**
  - `403 Forbidden`: Si el usuario no es el dueño ni un administrador.
  - `404 Not Found`: Si la mascota no existe.

### 2.3. Eliminar una Mascota

- **Endpoint:** `DELETE /api/pets/{id}`
- **Descripción:** Elimina una mascota.
- **Autenticación:** Requerida (`Bearer Token`). Solo el dueño de la mascota o un administrador pueden realizar esta acción.
- **Parámetros de Ruta:**
  - `id` (Long): El ID de la mascota a eliminar.
- **Respuesta Exitosa (200 OK):** Sin contenido.
- **Respuestas de Error:**
  - `403 Forbidden`: Si el usuario no es el dueño ni un administrador.
  - `404 Not Found`: Si la mascota no existe.

### 2.4. Obtener una Mascota por ID

- **Endpoint:** `GET /api/pets/{id}`
- **Descripción:** Obtiene los detalles de una mascota específica.
- **Autenticación:** No requerida.
- **Respuesta Exitosa (200 OK):** Devuelve el objeto `PetDto`.

### 2.5. Obtener Mascotas por Dueño

- **Endpoint:** `GET /api/pets/owner/{ownerId}`
- **Descripción:** Obtiene una lista de todas las mascotas de un usuario específico.
- **Autenticación:** No requerida.
- **Respuesta Exitosa (200 OK):** Devuelve una lista de objetos `PetDto`.

---

## 3. Administración (`/api/admin`)

Endpoints para la gestión de la plataforma. **Todos los endpoints en esta sección requieren rol de `ADMIN`**.

### 3.1. Obtener Todos los Usuarios

- **Endpoint:** `GET /api/admin/users`
- **Descripción:** Devuelve una lista de todos los usuarios registrados en el sistema.
- **Autenticación:** Requerida (`Bearer Token` con rol `ADMIN`).
- **Respuesta Exitosa (200 OK):** Devuelve una lista de objetos `UserAdminDto`.

### 3.2. Obtener Todas las Mascotas

- **Endpoint:** `GET /api/admin/pets`
- **Descripción:** Devuelve una lista de todas las mascotas registradas en la plataforma.
- **Autenticación:** Requerida (`Bearer Token` con rol `ADMIN`).
- **Respuesta Exitosa (200 OK):** Devuelve una lista de objetos `PetDto`.

---

## 4. Feed, Swipes, Matches y Mensajes

Estos controladores gestionan la lógica principal de la aplicación (el "Tinder" de mascotas).

### 4.1. Feed (`/api/feed`)

- **Endpoint:** `GET /api/feed`
- **Descripción:** Obtiene un feed de mascotas disponibles para que el usuario actual interactúe.
- **Autenticación:** Requerida (`Bearer Token`).
- **Respuesta Exitosa (200 OK):** Devuelve una lista paginada de `PetDto`.

### 4.2. Swipes (`/api/swipes`)

- **Endpoint:** `POST /api/swipes`
- **Descripción:** Registra un "swipe" (like o dislike) de un usuario hacia una mascota.
- **Autenticación:** Requerida (`Bearer Token`).
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "swiperPetId": 1,
    "swipedPetId": 2,
    "liked": true
  }
  ```
- **Respuesta Exitosa (200 OK):** Devuelve un objeto `SwipeDto` con los detalles del swipe y si se produjo un "match".

### 4.3. Matches (`/api/matches`)

- **Endpoint:** `GET /api/matches`
- **Descripción:** Obtiene una lista de todos los "matches" de las mascotas del usuario actual.
- **Autenticación:** Requerida (`Bearer Token`).
- **Respuesta Exitosa (200 OK):** Devuelve una lista de `MatchDto`.

### 4.4. Mensajes (`/api/messages`)

- **Endpoint:** `GET /api/messages/{matchId}`
- **Descripción:** Obtiene el historial de mensajes para un "match" específico.
- **Autenticación:** Requerida (`Bearer Token`).
- **Respuesta Exitosa (200 OK):** Devuelve una lista de `MessageDto`.

- **Endpoint:** `POST /api/messages`
- **Descripción:** Envía un nuevo mensaje en el contexto de un "match".
- **Autenticación:** Requerida (`Bearer Token`).
- **Cuerpo de la Solicitud (`application/json`):**
  ```json
  {
    "matchId": 1,
    "senderId": 1,
    "content": "¡Hola! ¿Quedamos en el parque?"
  }
  ```
- **Respuesta Exitosa (200 OK):** Devuelve el `MessageDto` del mensaje enviado.
