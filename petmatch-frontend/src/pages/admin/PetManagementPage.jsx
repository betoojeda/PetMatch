import React, { useState, useEffect } from 'react';
import PetForm from '../../components/PetForm';
import { useAuth } from '../../context/AuthContext';

const PetManagementPage = () => {
  const [pets, setPets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editingPet, setEditingPet] = useState(null);
  const [showAddForm, setShowAddForm] = useState(false);
  const { token } = useAuth();

  const fetchPets = async () => {
    setLoading(true);
    try {
      const response = await fetch('/api/admin/pets', {
        headers: { 'Authorization': `Bearer ${token}` },
      });
      const data = await response.json();
      setPets(data);
    } catch (error) {
      console.error("Error al cargar mascotas:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPets();
  }, [token]);

  const handleAddClick = () => {
    setEditingPet(null);
    setShowAddForm(true);
  };

  const handleEditClick = (pet) => {
    setEditingPet(pet);
    setShowAddForm(true);
  };

  const handleDeleteClick = async (petId) => {
    if (window.confirm('¿Estás seguro de que quieres eliminar esta mascota?')) {
      try {
        await fetch(`/api/pets/${petId}`, {
          method: 'DELETE',
          headers: { 'Authorization': `Bearer ${token}` },
        });
        fetchPets(); // Recargar la lista de mascotas
      } catch (error) {
        console.error("Error al eliminar la mascota:", error);
      }
    }
  };

  const handleFormClose = () => {
    setShowAddForm(false);
    setEditingPet(null);
    fetchPets(); // Recargar la lista después de añadir/editar
  };

  if (loading) {
    return <p>Cargando mascotas...</p>;
  }

  return (
    <div className="pet-management-page">
      <h2>Gestión de Mascotas</h2>
      
      {!showAddForm && (
        <button onClick={handleAddClick}>Añadir Nueva Mascota</button>
      )}

      {showAddForm && (
        <PetForm pet={editingPet} onClose={handleFormClose} />
      )}

      <h3>Todas las Mascotas</h3>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especie</th>
            <th>Raza</th>
            <th>Dueño ID</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {pets.map(pet => (
            <tr key={pet.id}>
              <td>{pet.id}</td>
              <td>{pet.name}</td>
              <td>{pet.type}</td>
              <td>{pet.breed}</td>
              <td>{pet.ownerId}</td>
              <td>
                <button onClick={() => handleEditClick(pet)}>Editar</button>
                <button onClick={() => handleDeleteClick(pet.id)}>Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PetManagementPage;
