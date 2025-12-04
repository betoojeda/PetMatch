import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import CreatePet from "./pages/CreatePet";
import EditPet from "./pages/EditPet";
import ViewPet from "./pages/ViewPet";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/pets/create" element={<CreatePet />} />
                <Route path="/pets/edit/:id" element={<EditPet />} />
                <Route path="/pets/view/:id" element={<ViewPet />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
