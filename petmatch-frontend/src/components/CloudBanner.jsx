import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const CloudBanner = () => {
  return (
    <div className="cloud-banner-container">
      {/* El SVG define la forma de la nube, los estilos están en App.css */}
      <svg className="cloud-svg" viewBox="0 0 340 140" preserveAspectRatio="xMidYMid meet">
        <path d="M60,130 A40,40 0 0,1 60,50 A30,30 0 0,1 120,50 A40,40 0 0,1 200,60 A35,35 0 0,1 280,90 A25,25 0 0,1 280,130 Z" />
      </svg>
      
      {/* El contenido se superpone al SVG */}
      <div className="cloud-banner-content">
        <div className="public-brand">
          <span>petmatch</span>
        </div>
        <nav className="public-nav">
          <Link to="/about" className="public-nav-link">Acerca de</Link>
          <Link to="/login" className="public-nav-button">Iniciar Sesión</Link>
          <Link to="/register" className="public-nav-button primary">Regístrate</Link>
        </nav>
      </div>
    </div>
  );
};

export default CloudBanner;
