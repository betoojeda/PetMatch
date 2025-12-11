import apiClient from './api';

export const logError = async (error) => {
  try {
    const logData = {
      level: 'ERROR',
      message: error.message,
      // Limitar el tamaño del stack trace a 2000 caracteres
      stack: error.stack ? String(error.stack).substring(0, 2000) : 'No stack trace available',
    };
    // Usamos 'fire and forget', no necesitamos esperar la respuesta
    apiClient.post('/logs', logData);
  } catch (e) {
    // Si el servicio de logs falla, lo mostramos en la consola para no entrar en un bucle
    console.error('Failed to send log to server:', e);
  }
};
