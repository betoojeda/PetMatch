import { useEffect, useState } from "react";
import { getPet } from "../services/petService";
import { useParams } from "react-router-dom";
import PetDetails from "../components/PetDetails";

function ViewPet() {
    const { id } = useParams();
    const [pet, setPet] = useState(null);

    useEffect(() => {
        getPet(id).then((res) => setPet(res.data));
    }, [id]);

    return <PetDetails pet={pet} />;
}

export default ViewPet;
