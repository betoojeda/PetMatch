import React from 'react';
import './LostPetCard.css';

const LostPetCard = ({ post }) => {
  const { petName, description, photoUrl, location, status, createdAt, user, comments } = post;

  return (
    <div className="lost-pet-card">
      <img src={photoUrl || 'https://via.placeholder.com/300'} alt={petName} className="lost-pet-photo" />
      <div className="lost-pet-info">
        <h3>{petName}</h3>
        <span className={`status-badge status-${status?.toLowerCase()}`}>{status}</span>
        <p><strong>Visto por última vez en:</strong> {location}</p>
        <p>{description}</p>
        <small>Publicado por {user?.name} el {new Date(createdAt).toLocaleDateString()}</small>
      </div>
      <div className="lost-pet-comments">
        <h4>Comentarios ({comments?.length || 0})</h4>
        {comments && comments.length > 0 ? (
          comments.map(comment => (
            <div key={comment.id} className="comment">
              <p><strong>{comment.user?.name}:</strong> {comment.text}</p>
              <small>{new Date(comment.createdAt).toLocaleString()}</small>
            </div>
          ))
        ) : (
          <p>No hay comentarios aún.</p>
        )}
        {/* Aquí irá el formulario para añadir un nuevo comentario */}
      </div>
    </div>
  );
};

export default LostPetCard;
