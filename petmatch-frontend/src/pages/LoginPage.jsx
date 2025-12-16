import React, { useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import logo from '../assets/LogoSinFondo.png';
import '../styles/auth.css';
import toast from 'react-hot-toast';
import LoadingModal from '../components/LoadingModal';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  // Determinar la ruta de regreso. Por defecto, al Hub.
  const from = location.state?.from || '/';

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const { role } = await login(email, password);
      toast.success('¡Bienvenido de nuevo!');
      if (role === 'ADMIN') {
        navigate('/admin');
      } else {
        navigate('/dashboard');
      }
    } catch (err) {
      toast.error(err.message || 'Error al iniciar sesión. Revisa tus credenciales.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      {isLoading && <LoadingModal message="Iniciando sesión..." />}
      <div className="auth-page-container">
        <div className="form-container">
          <img src={logo} alt="petmatch Logo" className="auth-logo" />
          <h2>Iniciar Sesión</h2>
          <form onSubmit={handleSubmit}>
            {/* ... (resto del formulario) ... */}
            <div className="auth-links">
              <p>¿No tienes cuenta? <Link to="/register" state={{ from }}>Regístrate</Link></p>
              <p><Link to="/forgot-password">¿Olvidaste tu contraseña?</Link></p>
              <Link to={from} className="back-to-home-button">Volver</Link>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default LoginPage;
