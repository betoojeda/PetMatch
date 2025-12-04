import { createPet } from "../services/petService";
import { useNavigate } from "react-router-dom";
import PetForm from "../components/PetForm";

function CreatePet() {
    const navigate = useNavigate();

    const onSubmit = async (e) => {
        e.preventDefault();
        const form = new FormData(e.target);

        const pet = Object.fromEntries(form.entries());

        await createPet(pet);
        navigate("/");
    };

    return <PetForm onSubmit={onSubmit} />;
}

export default CreatePet;
