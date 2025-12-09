# Etapa 1: Construir la aplicación React
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install --legacy-peer-deps
COPY . .
# Pasar la URL de la API como argumento de construcción a Vite
ARG VITE_REACT_APP_API_URL
ENV VITE_REACT_APP_API_URL=${VITE_REACT_APP_API_URL}
RUN npm run build

# Etapa 2: Servir la aplicación con Nginx
FROM nginx:stable-alpine

# Copiar los archivos de la aplicación
COPY --from=build /app/dist /usr/share/nginx/html

# Copiar la plantilla de configuración de Nginx al lugar correcto
COPY nginx.conf /etc/nginx/conf.d/default.conf.template

# Crear el script de inicio directamente en el Dockerfile
RUN echo '#!/bin/sh' > /start.sh && \
    echo 'BACKEND_URL_VALUE=${BACKEND_URL:-http://backend:8080}' >> /start.sh && \
    echo 'echo "Setting backend URL to: $BACKEND_URL_VALUE"' >> /start.sh && \
    echo 'sed -i "s|BACKEND_URL_PLACEHOLDER|$BACKEND_URL_VALUE|g" /etc/nginx/conf.d/default.conf.template' >> /start.sh && \
    echo 'mv /etc/nginx/conf.d/default.conf.template /etc/nginx/conf.d/default.conf' >> /start.sh && \
    echo 'nginx -g "daemon off;"' >> /start.sh && \
    chmod +x /start.sh

EXPOSE 80

# Ejecutar el script de inicio
CMD ["/start.sh"]
