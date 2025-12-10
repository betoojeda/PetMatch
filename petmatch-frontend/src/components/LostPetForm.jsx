import React, { useState } from 'react';
import { createLostPetPost } from '../services/lostPetsService';
import { uploadPetPhoto } from '../services/api'; // Reutilizamos el servicio de subida
import toast from 'react-hot-toast';
import LoadingModal from './LoadingModal';

const LostPetForm = ({ onClose, onSaveSuccess }) => {
  const [formData, setFormData] = useState({
    petName: '',
    description: '',
    location: '',
  });
  const [file, setFile] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) {
      toast.error('Por favor, sube una foto de la mascota.');
      return;
    }
    setIsSubmitting(true);

    try {
      // 1. Crear el post con los datos de texto
      const newPost = await createLostPetPost(formData);
      
      // 2. Subir la foto al post recién creado
      const finalPost = await uploadPetPhoto(newPost.id, file, '/lost-pets'); // Usamos un nuevo path

      toast.success('Aviso publicado con éxito.');
      onSaveSuccess(finalPost);
      onClose();
    } catch (error) {
      toast.error(error.message);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <>
      {isSubmitting && <LoadingModal message="Publicando aviso..." />}
      <div className="form-container">
        <h2>Reportar Mascota Perdida</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-row">
            <label>Nombre de la Mascota</label>
            <input type="text" name="petName" value={formData.petName} onChange={handleChange} className="form-input" required />
          </div>
          <div className="form-row">
            <label>Última ubicación conocida</label>
            <input type="text" name="location" value={formData.location} onChange={handleChange} className="form-input" placeholder="Ej: Cerca del Parque Central, Barrio..." required />
          </div>
          <div className="form-row">
            <label>Descripción</label>
            <textarea name="description" value={formData.description} onChange={handleChange} className="form-input" placeholder="Describe a la mascota, ropa que llevaba, etc." required></textarea>
          </div>
          <div className="form-row">
            <label>Foto</label>
            <input type="file" accept="image/*" onChange={handleFileChange} className="form-input" required />
          </div>
          <div className="form-actions">
            <button type="button" onClick={onClose} className="button-secondary" disabled={isSubmitting}>Cancelar</button>
            <button type="submit" className="button-primary" disabled={isSubmitting}>Publicar Aviso</button>
          </div>
        </form>
      </div>
    </>
  );
};

export default LostPetForm;
