import React from 'react';
import PetImageGallery from './PetImageGallery';
import './PetProfileModal.css';

const PetProfileModal = ({ pet, onClose }) => {
  if (!pet) return null;

  const DetailItem = ({ label, value, isBoolean = false }) => (
    <div className="detail-item">
      <span className="detail-label">{label}:</span>
      <span className="detail-value">
        {isBoolean ? (value ? 'Sí' : 'No') : (value || 'No especificado')}
      </span>
    </div>
  );

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="modal-close-button" onClick={onClose}>&times;</button>
        
        <div className="modal-header">
          <h2>{pet.name}</h2>
          <p className="breed-subtitle">{pet.breed} - {pet.type}</p>
        </div>

        <div className="modal-body">
          <PetImageGallery images={pet.photoUrls} />

          <div className="pet-details-grid">
            <DetailItem label="Edad" value={`${pet.age} años`} />
            <DetailItem label="Género" value={pet.gender} />
            <DetailItem label="Tamaño" value={pet.size} />
            <DetailItem label="Nivel de Energía" value={pet.energyLevel} />
            <DetailItem label="Temperamento" value={pet.temperament} />
            <DetailItem label="Nivel de Entrenamiento" value={pet.trainingLevel} />
            
            <div className="detail-item full-width">
              <span className="detail-label">Descripción:</span>
              <p className="detail-text">{pet.description || 'No hay descripción disponible.'}</p>
            </div>
            
            <div className="detail-item full-width">
              <span className="detail-label">Historia:</span>
              <p className="detail-text">{pet.history || 'No hay historia disponible.'}</p>
            </div>

            <div className="detail-item full-width">
              <span className="detail-label">Necesidades Especiales:</span>
              <p className="detail-text">{pet.specialNeeds || 'Ninguna.'}</p>
            </div>

            <h3 className="details-subheader">Compatibilidad y Salud</h3>
            <DetailItem label="Compatible con Perros" value={pet.compatibleWithDogs} isBoolean />
            <DetailItem label="Compatible con Gatos" value={pet.compatibleWithCats} isBoolean />
            <DetailItem label="Compatible con Niños" value={pet.compatibleWithChildren} isBoolean />
            <DetailItem label="Vacunado" value={pet.vaccinated} isBoolean />
            <DetailItem label="Desparasitado" value={pet.dewormed} isBoolean />
            <DetailItem label="Esterilizado" value={pet.sterilized} isBoolean />
          </div>
        </div>
      </div>
    </div>
  );
};

export default PetProfileModal;
