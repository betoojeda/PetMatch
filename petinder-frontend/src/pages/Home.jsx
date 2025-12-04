import { useEffect, useState } from "react";
import { getPets, deletePet } from "../services/petService";
import PetList from "../components/PetList";

function Home() {
    const [pets, setPets] = useState([]);

    const loadPets = async () => {
        const res = await getPets();
        setPets(res.data);
    };

    const onDelete = async (id) => {
        await deletePet(id);
        loadPets();
    };

    useEffect(() => {
        loadPets();
    }, []);

    return <PetList pets={pets} onDelete={onDelete} />;
}

export default Home;
