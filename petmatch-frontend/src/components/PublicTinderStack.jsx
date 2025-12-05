import React, { useState, useEffect } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import '../App.css';

const publicPets = [
  { id: 1, name: 'Max', age: 3, breed: 'Golden Retriever', description: 'Amante de los parques y las pelotas.', photoUrl: 'https://images.unsplash.com/photo-1583512603805-3cc6b41f3edb?q=80&w=1932&auto=format&fit=crop' },
  { id: 2, name: 'Lucy', age: 2, breed: 'Pug', description: 'Experta en siestas y recibir mimos.', photoUrl: 'https://images.unsplash.com/photo-1517849845537-4d257902454a?q=80&w=1935&auto=format&fit=crop' },
  { id: 3, name: 'Rocky', age: 5, breed: 'Boxer', description: 'Un alma juguetona con energía sin fin.', photoUrl: 'https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?q=80&w=1964&auto=format&fit=crop' },
  { id: 4, name: 'Bella', age: 1, breed: 'Labrador', description: 'Curiosa y siempre lista para una nueva aventura.', photoUrl: 'https://images.unsplash.com/photo-1592194991134-93349f4a79de?q=80&w=1887&auto=format&fit=crop' },
  { id: 5, name: 'Charlie', age: 4, breed: 'Beagle', description: 'Un detective de olores en busca de un cómplice.', photoUrl: 'https://images.unsplash.com/photo-1552053831-71594a27632d?q=80&w=1862&auto=format&fit=crop' }
];

const cardVariants = {
  initial: { x: 0, y: 0, opacity: 1 },
  exit: (direction) => ({
    x: direction === 'right' ? 300 : -300,
    y: 20,
    opacity: 0,
    transition: { duration: 0.5 }
  })
};

const PublicTinderStack = () => {
  const [index, setIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setIndex(prevIndex => (prevIndex + 1) % publicPets.length);
    }, 3000);

    return () => clearInterval(interval);
  }, []);

  const pet = publicPets[index];
  const direction = Math.random() > 0.5 ? 'right' : 'left';

  return (
    <div className="public-card-container">
      <AnimatePresence initial={false} custom={direction}>
        <motion.div
          key={pet.id}
          className="card"
          variants={cardVariants}
          initial="initial"
          animate="initial"
          exit="exit"
          custom={direction}
          style={{ backgroundImage: `url(${pet.photoUrl})` }}
        >
          <div className="card-info">
            <h3>{pet.name}, {pet.age} años</h3>
            <p className="card-breed">{pet.breed}</p>
            <p className="card-description">"{pet.description}"</p>
          </div>
        </motion.div>
      </AnimatePresence>
    </div>
  );
};

export default PublicTinderStack;
