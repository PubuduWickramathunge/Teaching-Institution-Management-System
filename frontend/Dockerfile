FROM node:18.2.0-alpine
WORKDIR '/frontend'
COPY package.json .
RUN npm install
COPY . .
CMD ["npm","start"]