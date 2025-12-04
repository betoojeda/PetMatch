function PetForm({ pet, onSubmit }) {
    return (
        <form onSubmit={onSubmit}>
            <input
                name="name"
                placeholder="Nombre"
                defaultValue={pet?.name}
            />

            <input
                name="species"
                placeholder="Especie"
                defaultValue={pet?.species}
            />

            <input
                name="breed"
                placeholder="Raza"
                defaultValue={pet?.breed}
            />

            <input
                name="age"
                type="number"
                placeholder="Edad"
                defaultValue={pet?.age}
            />

            <input
                name="photoUrl"
                placeholder="URL de Foto"
                defaultValue={pet?.photoUrl}
            />

            <textarea
                name="description"
                placeholder="Descripción"
                defaultValue={pet?.description}
            />

            <button type="submit">Guardar</button>
        </form>
    );
}

export default PetForm;
