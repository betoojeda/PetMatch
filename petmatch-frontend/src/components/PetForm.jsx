import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';

const PetForm = ({ pet, onClose }) => {
  const { token } = useAuth();
  const [formData, setFormData] = useState({
    name: '',
    type: '',
    breed: '',
    age: '',
    description: '',
    photoUrl: '',
    size: '',
    gender: '',
    energyLevel: '',
    temperament: '',
    compatibleWithDogs: false,
    compatibleWithCats: false,
    compatibleWithChildren: false,
    specialNeeds: '',
    trainingLevel: '',
    vaccinated: false,
    dewormed: false,
    sterilized: false,
    history: '',
    ownerId: '', // Necesario para la creación
  });

  useEffect(() => {
    if (pet) {
      setFormData({
        name: pet.name || '',
        type: pet.type || '',
        breed: pet.breed || '',
        age: pet.age || '',
        description: pet.description || '',
        photoUrl: pet.photoUrl || '',
        size: pet.size || '',
        gender: pet.gender || '',
        energyLevel: pet.energyLevel || '',
        temperament: pet.temperament || '',
        compatibleWithDogs: pet.compatibleWithDogs || false,
        compatibleWithCats: pet.compatibleWithCats || false,
        compatibleWithChildren: pet.compatibleWithChildren || false,
        specialNeeds: pet.specialNeeds || '',
        trainingLevel: pet.trainingLevel || '',
        vaccinated: pet.vaccinated || false,
        dewormed: pet.dewormed || false,
        sterilized: pet.sterilized || false,
        history: pet.history || '',
        ownerId: pet.ownerId || '',
      });
    }
  }, [pet]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = pet ? `/api/pets/${pet.id}` : '/api/pets';
    const method = pet ? 'PUT' : 'POST';

    try {
      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error(pet ? 'Error al actualizar la mascota' : 'Error al crear la mascota');
      }
      
      onClose();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="pet-form-container">
      <h3>{pet ? 'Editar Mascota' : 'Añadir Nueva Mascota'}</h3>
      <form onSubmit={handleSubmit}>
        {/* Aquí irían todos los campos del formulario, igual que antes */}
        <label>
          Nombre:
          <input type="text" name="name" value={formData.name} onChange={handleChange} required />
        </label>
        <label>
          Dueño ID (solo para creación):
          <input type="number" name="ownerId" value={formData.ownerId} onChange={handleChange} disabled={!!pet} />
        </label>
        {/* ... (el resto de los campos del formulario) ... */}
        <button type="submit">{pet ? 'Guardar Cambios' : 'Añadir Mascota'}</button>
        <button type="button" onClick={onClose}>Cancelar</button>
      </form>
    </div>
  );
};

export default PetForm;
