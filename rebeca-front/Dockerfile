# Estagio 1 - Será responsavel em construir a aplicação
FROM node:12.2.0-slim as node
WORKDIR /app
COPY package.json /app/
RUN npm i -g @angular/cli@11.2.11
RUN npm install
COPY ./ /app/
ARG env=prod
RUN npm run build

# Estagio 2 - Será responsavel por expor a aplicação
FROM nginx:1.13
COPY --from=node /app/dist/rebeca-front /usr/share/nginx/html
COPY ./nginx-custom.conf /etc/nginx/conf.d/default.conf
