import apiClient from './api';

export const getLostPets = async (page = 0, size = 10) => {
  try {
    const response = await apiClient.get(`/lost-pets?page=${page}&size=${size}`);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al cargar los avisos de mascotas perdidas');
  }
};

export const createLostPetPost = async (postData) => {
  try {
    const response = await apiClient.post('/lost-pets', postData);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al publicar el aviso');
  }
};

export const addComment = async (postId, text) => {
  try {
    const response = await apiClient.post(`/lost-pets/${postId}/comments`, { text });
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al publicar el comentario');
  }
};
