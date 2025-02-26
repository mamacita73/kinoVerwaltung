# Basis-Image für Node.js
FROM node:18-alpine

# Setze das Arbeitsverzeichnis
WORKDIR /app

# Kopiere package.json und package-lock.json
COPY package*.json ./

# Installiere Abhängigkeiten
RUN npm install

# Kopiere den restlichen Code
COPY . .

# Baue die Anwendung (falls es sich um React/Vite handelt)
RUN npm run build

# Exponiere den Port für das Frontend
EXPOSE 3000

# Starte die Anwendung
CMD ["npm", "start"]
