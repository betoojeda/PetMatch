# PetMatch - Encuentra la Pareja Perfecta para tu Mascota

PetMatch es una aplicación web moderna y divertida, inspirada en el formato de Tinder, diseñada para que los dueños de mascotas puedan encontrar la pareja ideal para sus compañeros peludos. Ya sea para jugar, socializar o encontrar un alma gemela, PetMatch facilita la conexión entre mascotas compatibles en tu área.

## ¿Qué es PetMatch?

La aplicación permite a los usuarios crear perfiles detallados para sus mascotas, incluyendo fotos, raza, nivel de energía, temperamento y compatibilidades. Luego, pueden deslizar a través de los perfiles de otras mascotas: a la derecha para "Me gusta" y a la izquierda para "Pasar". Si dos mascotas se gustan mutuamente, ¡es un "Match"! A partir de ese momento, los dueños pueden chatear para coordinar encuentros.

## Tecnologías Utilizadas

-   **Backend:** Java 17, Spring Boot 3, Spring Security, JPA (Hibernate), PostgreSQL, Lombok, Docker.
-   **Frontend:** React 18, Vite, React Router, Axios, CSS Moderno, Docker, Nginx.

---

## Cómo Empezar (Entorno de Desarrollo)

Sigue estos pasos para levantar la aplicación en tu máquina local.

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/petmatch.git
    cd petmatch
    ```

2.  **Crea tu archivo de entorno:**
    Crea un archivo llamado `.env` en la raíz del proyecto, copiando el contenido de `.env.example`. Este archivo contendrá todas las contraseñas y secretos.
    ```bash
    cp .env.example .env
    ```
    **Importante:** Revisa y ajusta las variables en el archivo `.env`. Necesitarás configurar tus propias credenciales para el servicio de correo (por ejemplo, una contraseña de aplicación de Gmail).

3.  **Levanta los contenedores:**
    Usa Docker Compose para construir y levantar todos los servicios.
    ```bash
    docker-compose up --build
    ```

4.  **Accede a la aplicación:**
    -   El frontend estará disponible en `http://localhost:3000`.
    -   La API del backend estará en `http://localhost:8080`.

5.  **Credenciales de prueba:**
    -   **Administrador:** `admin@petmatch.com` / `admin01`
    -   **Usuario normal:** Puedes registrar uno nuevo.

---

## Manual de Despliegue en Producción

Esta guía describe los pasos para desplegar PetMatch en un servidor de producción (por ejemplo, una instancia de AWS EC2, DigitalOcean, etc.).

### Prerrequisitos

-   Un servidor con **Docker** y **Docker Compose** instalados.
-   Un **nombre de dominio** (por ejemplo, `www.petmatch.com`) apuntando a la IP pública de tu servidor.
-   **Certificados SSL/TLS** para tu dominio para habilitar HTTPS. Se recomienda usar [Let's Encrypt](https://letsencrypt.org/).

### Pasos para el Despliegue

1.  **Clona el repositorio en el servidor:**
    ```bash
    git clone https://github.com/tu-usuario/petmatch.git
    cd petmatch
    ```

2.  **Configura las variables de entorno para producción:**
    Crea el archivo `.env` a partir de la plantilla.
    ```bash
    cp .env.example .env
    ```
    Abre el archivo (`nano .env`) y configúralo para producción:
    -   `POSTGRES_PASSWORD`: Usa una contraseña fuerte y segura.
    -   `JWT_SECRET`: Genera una cadena aleatoria larga y segura.
    -   `SPRING_MAIL_PASSWORD`: La contraseña de tu proveedor de correo.
    -   `FRONTEND_URL`: La URL pública de tu frontend (ej. `https://www.petmatch.com`).
    -   `REACT_APP_API_URL`: La URL pública de tu backend (ej. `https://api.petmatch.com` o `https://www.petmatch.com/api`).

3.  **Construye y levanta los servicios en modo "detached":**
    El flag `-d` ejecuta los contenedores en segundo plano.
    ```bash
    docker-compose up --build -d
    ```

4.  **Configura un Reverse Proxy con Nginx (Recomendado):**
    Aunque el frontend se sirve con Nginx dentro de Docker, es una buena práctica usar un Nginx a nivel de sistema operativo como reverse proxy. Esto facilita la gestión de dominios y la configuración de SSL.

    -   Instala Nginx en tu servidor (`sudo apt-get install nginx`).
    -   Crea un archivo de configuración para tu sitio (ej. `/etc/nginx/sites-available/petmatch`).
    -   Configúralo para redirigir el tráfico del puerto 80 (HTTP) y 443 (HTTPS) a tu contenedor del frontend (que corre en el puerto 3000).
    -   Activa la configuración y reinicia Nginx.

5.  **Verifica el estado de los contenedores:**
    ```bash
    docker-compose ps
    ```
    Asegúrate de que todos los servicios (`db`, `backend`, `frontend`) estén en estado `Up`.

6.  **Monitoriza los logs:**
    Si algo sale mal, puedes ver los logs de cada servicio.
    ```bash
    docker-compose logs -f backend
    docker-compose logs -f frontend
    ```

### Consideraciones de Seguridad

-   **Nunca subas tu archivo `.env` a `git`**. Asegúrate de que `.env` esté en tu archivo `.gitignore`.
-   Usa contraseñas y secretos fuertes en tu `.env` de producción.
-   Mantén tu servidor y Docker actualizados con los últimos parches de seguridad.
-   Configura un firewall en tu servidor para permitir tráfico solo en los puertos necesarios (80, 443, 22).
