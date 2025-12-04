import { useEffect, useState } from "react";
import { getPet, updatePet } from "../services/petService";
import { useNavigate, useParams } from "react-router-dom";
import PetForm from "../components/PetForm";

function EditPet() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [pet, setPet] = useState(null);

    useEffect(() => {
        getPet(id).then((res) => setPet(res.data));
    }, [id]);

    const onSubmit = async (e) => {
        e.preventDefault();
        const form = new FormData(e.target);
        const updated = Object.fromEntries(form.entries());
        await updatePet(id, updated);
        navigate("/");
    };

    return <PetForm pet={pet} onSubmit={onSubmit} />;
}

export default EditPet;
