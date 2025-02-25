import { useEffect, useState } from "react";
import { getBenutzer } from "../services/api";

function Benutzer() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getBenutzer().then(setUsers);
    }, []);

    return (
        <div>
            <h1>Benutzerliste</h1>
            <ul>
                {users.map((user) => (
                    <li key={user.id}>{user.name} ({user.email})</li>
                ))}
            </ul>
        </div>
    );
}

export default Benutzer;
