import React from 'react';
import { Link } from 'react-router-dom';
import HeroImageCarousel from '../components/HeroImageCarousel';
import StatsCounter from '../components/StatsCounter';
import './PublicHomePage.css'; // Carga sus propios estilos

const PublicHomePage = () => {
  return (
    // Contenedor único para esta página
    <div className="landing-page"> 
      <header className="landing-header">
        <div className="landing-logo">PetMatch</div>
        <nav className="landing-nav">
          <Link to="/about" className="landing-nav-btn">Acerca de</Link>
          <Link to="/login" className="landing-nav-btn">Iniciar Sesión</Link>
          <Link to="/register" className="landing-nav-btn primary">Regístrate</Link>
        </nav>
      </header>

      <main className="landing-main">
        <div className="hero-text-box">
          <h1 className="hero-title">Conexiones Reales para Amigos Peludos.</h1>
          <p className="hero-subtitle">La app para encontrar compañeros de juego, amigos y hasta el alma gemela de tu mascota.</p>
        </div>
        
        <div className="hero-image-container">
          <HeroImageCarousel />
        </div>

        <Link to="/register" className="hero-cta-btn">Únete a la Comunidad</Link>
      </main>

      <footer className="landing-stats">
        <div className="stat-item">
          <div className="stat-number" style={{ color: '#e55' }}>+<StatsCounter end={1200} /></div>
          <div className="stat-label">Matches Hoy</div>
        </div>
        <div className="stat-item">
          <div className="stat-number" style={{ color: '#5cde5c' }}>+<StatsCounter end={5000} /></div>
          <div className="stat-label">Miembros Activos</div>
        </div>
        <div className="stat-item">
          <div className="stat-number" style={{ color: '#f7b733' }}>+<StatsCounter end={800} /></div>
          <div className="stat-label">Citas Organizadas</div>
        </div>
      </footer>
    </div>
  );
};

export default PublicHomePage;
