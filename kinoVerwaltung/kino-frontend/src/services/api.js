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
export const registerUser = async (benutzername, email, passwort, rolle) => {
    try {
        const response = await fetch("http://localhost:8080/benutzer/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ benutzername, email, passwort, rolle })
        });

        return await response.json();
    } catch (error) {
        console.error("Fehler bei der Registrierung:", error);
        return { success: false, error: error.message };
    }
};


export const loginUser = async (email, password) => {
    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, passwort: password }) // Nur E-Mail & Passwort senden
        });

        if (!response.ok) {
            throw new Error("Falsche E-Mail oder Passwort");
        }

        return await response.text(); // Server gibt "KUNDE" oder "ADMIN" zurück
    } catch (error) {
        return { error: error.message };
    }
};

