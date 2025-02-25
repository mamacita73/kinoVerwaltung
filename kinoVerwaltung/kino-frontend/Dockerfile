# Basis-Image für Node.js
FROM node:18-alpine

# Arbeitsverzeichnis setzen
WORKDIR /app

# Abhängigkeiten kopieren
COPY package.json package-lock.json ./
RUN npm install

# Quellcode kopieren
COPY . .

# Build ausführen
RUN npm run build

# Webserver starten
CMD ["npm", "start"]
