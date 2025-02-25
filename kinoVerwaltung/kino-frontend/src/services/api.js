import axios from "axios";

const API_URL = "http://localhost:8080/api";  // URL des Spring Boot Backends

// Benutzer abrufen
export const getBenutzer = async () => {
    try {
        const response = await axios.get(`${API_URL}/benutzer`);
        return response.data;
    } catch (error) {
        console.error("Fehler beim Laden der Benutzer", error);
        return [];
    }
};

// Benutzer hinzufügen
export const createBenutzer = async (benutzer) => {
    try {
        const response = await axios.post(`${API_URL}/benutzer`, benutzer);
        return response.data;
    } catch (error) {
        console.error("Fehler beim Erstellen des Benutzers", error);
    }
};
