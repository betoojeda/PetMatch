import apiClient from './api';

export const logError = async (error) => {
  try {
    const logData = {
      level: 'ERROR',
      message: error.message,
      stack: error.stack,
    };
    // Usamos 'fire and forget', no necesitamos esperar la respuesta
    apiClient.post('/logs', logData);
  } catch (e) {
    // Si el servicio de logs falla, lo mostramos en la consola para no entrar en un bucle
    console.error('Failed to send log to server:', e);
  }
};
