import React from 'react';
import { Link } from 'react-router-dom';
import PublicTinderStack from '../components/PublicTinderStack';
import '../App.css';

const PublicHomePage = () => {
  return (
    <div className="public-homepage-container">
      <header className="public-header">
        <div className="public-brand">
          <span>PetMatch</span>
        </div>
        <nav className="public-nav">
          <Link to="/about" className="public-nav-link">Acerca de</Link>
          <Link to="/login" className="public-nav-button">Iniciar Sesión</Link>
          <Link to="/register" className="public-nav-button primary">Regístrate</Link>
        </nav>
      </header>
      <main className="public-main-content">
        <div className="hero-section">
          <h1>La Aplicación de Citas para Mascotas... y quizás para sus dueños también</h1>
          <div className="slogan-banner">
            <p>Encuentra amigos y parejas para tu compañero peludo. Desliza, conecta y organiza citas de juego.</p>
          </div>
        </div>
        <PublicTinderStack />
      </main>
    </div>
  );
};

export default PublicHomePage;
