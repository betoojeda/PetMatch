import axios from 'axios';

// ... (código existente)

export const uploadPetPhoto = async (entityId, file, basePath = '/pets') => {
  const formData = new FormData();
  formData.append('file', file);

  try {
    // Usamos el basePath para construir la URL dinámicamente
    const response = await apiClient.post(`${basePath}/${entityId}/photo`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || 'Error al subir la foto');
  }
};

// ... (resto del código)
