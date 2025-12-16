import React, { useState } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import logo from '../assets/LogoSinFondo.png';
import '../styles/auth.css';
import toast from 'react-hot-toast';
import LoadingModal from '../components/LoadingModal';

const RegisterPage = () => {
  const [formData, setFormData] = useState({
    name: '', lastName: '', email: '', password: '',
    gender: '', numberOfPets: 0, profileDescription: '',
  });
  const [interest, setInterest] = useState('PetMatch');
  const [isLoading, setIsLoading] = useState(false);
  const { register } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  // Determinar la ruta de regreso. Por defecto, al Hub.
  const from = location.state?.from || '/';

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    
    const finalFormData = {
      ...formData,
      profileDescription: `Interés principal: ${interest}. ${formData.profileDescription}`
    };

    try {
      await register(finalFormData);
      toast.success('¡Registro exitoso! Bienvenido a PetMatch.');
      navigate('/dashboard');
    } catch (err) {
      toast.error(err.message || 'Error al registrarse. Inténtalo de nuevo.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      {isLoading && <LoadingModal message="Creando tu cuenta..." />}
      <div className="auth-page-container">
        <div className="form-container">
          <img src={logo} alt="petmatch Logo" className="auth-logo" />
          <h2>Crear Cuenta</h2>
          <form onSubmit={handleSubmit}>
            {/* ... (resto del formulario) ... */}
            <div className="auth-links">
              <p>¿Ya tienes cuenta? <Link to="/login">Inicia Sesión</Link></p>
              <Link to={from} className="back-to-home-button">Volver</Link>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default RegisterPage;
