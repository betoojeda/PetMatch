function PetDetails({ pet }) {
    if (!pet) return <p>Cargando...</p>;

    return (
        <div>
            <h2>{pet.name}</h2>
            <img src={pet.photoUrl} width="200" />
            <p><strong>Especie:</strong> {pet.species}</p>
            <p><strong>Raza:</strong> {pet.breed}</p>
            <p><strong>Edad:</strong> {pet.age}</p>
            <p>{pet.description}</p>
        </div>
    );
}

export default PetDetails;
