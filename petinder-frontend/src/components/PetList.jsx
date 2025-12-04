import { Link } from "react-router-dom";

function PetList({ pets, onDelete }) {
    return (
        <div>
            <h2>Mascotas</h2>
            <Link to="/pets/create">➕ Crear Mascota</Link>

            <ul>
                {pets.map((p) => (
                    <li key={p.id} style={{ marginTop: "10px" }}>
                        <strong>{p.name}</strong> — {p.species}
                        <br />
                        <Link to={`/pets/view/${p.id}`}>Ver</Link> |{" "}
                        <Link to={`/pets/edit/${p.id}`}>Editar</Link> |{" "}
                        <button onClick={() => onDelete(p.id)}>Eliminar</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default PetList;
