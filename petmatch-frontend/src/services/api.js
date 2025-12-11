import axios from 'axios';
import { logError } from './logService'; // Importar el servicio de logs

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL || '/api',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

// --- Interceptor de Errores ---
apiClient.interceptors.response.use(
  (response) => response, // Si la respuesta es exitosa, la devolvemos tal cual
  (error) => {
    // Si hay un error en la respuesta, lo enviamos a nuestro servicio de logs
    logError(error);
    // Rechazamos la promesa para que el error siga su curso y se maneje en el catch del componente
    return Promise.reject(error);
  }
);


// --- Funciones de Autenticación ---
export const login = async (credentials) => {
  try {
    const response = await apiClient.post('/auth/login', credentials);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al iniciar sesión');
  }
};

// ... (resto de las funciones sin cambios)
export const register = async (userData) => {
  try {
    const response = await apiClient.post('/auth/register', userData);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error en el registro');
  }
};

export const logout = async () => {
  try {
    await apiClient.post('/auth/logout');
  } catch (error) {
    console.error('Error al cerrar sesión en el servidor:', error);
  }
};

export const getMe = async () => {
  try {
    const response = await apiClient.get('/auth/me');
    return response.data;
  } catch (error) {
    if (error.response && error.response.status === 401) {
      return null;
    }
    throw new Error('Error al verificar la sesión');
  }
};

export const getFeed = async () => {
  try {
    const response = await apiClient.get('/feed');
    return response.data.content || [];
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al cargar el feed');
  }
};

export const swipe = async (petId, type) => {
  try {
    const response = await apiClient.post('/swipes', { petId, type });
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al procesar el swipe');
  }
};

export const uploadPetPhoto = async (entityId, file, basePath = '/pets') => {
  const formData = new FormData();
  formData.append('file', file);

  try {
    const response = await apiClient.post(`${basePath}/${entityId}/photos`, formData, { // Corregido: 'photo' a 'photos'
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al subir la foto');
  }
};

export const getPetsByOwner = async (ownerId) => {
  try {
    const response = await apiClient.get(`/pets/owner/${ownerId}`);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al cargar tus mascotas');
  }
};

export const getMatches = async () => {
  try {
    const response = await apiClient.get('/matches'); 
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al cargar los matches');
  }
};

export const getMessagesForMatch = async (matchId) => {
  try {
    const response = await apiClient.get(`/messages/match/${matchId}`);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al cargar los mensajes');
  }
};

export const sendMessage = async (matchId, senderUserId, text) => {
  try {
    const response = await apiClient.post('/messages', { matchId, senderUserId, text });
    return response.data;
  } catch (error)
    {
    throw new Error(error.response?.data?.message || 'Error al enviar el mensaje');
  }
};

export default apiClient;
