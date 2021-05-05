# Imagem Base
FROM node:12.2.0

# Instala o chrome para testes
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'
RUN apt-get update && apt-get install -yq google-chrome-stable

# Setando o diretório de trabalho
WORKDIR /app

# Adicionando o path '/app/node_modules/.bin' para a variável $PATH
ENV PATH /app/node_modules/.bin:$PATH

# instalar e armazenar em cache dependências do aplicativo
COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@7.3.9

# Executar testes
# RUN ng test --watch=false
# RUN ng e2e --port 4202

# Adiciona os arquivos do app
COPY . /app

############
# Configuração para produção
############

# Imagem base do nginx
# FROM nginx:1.16.0-alpine

# copiar o artefato de build
# COPY --from=build /app/dist /usr/share/nginx/html

# expose port 80
# EXPOSE 80

# run nginx
# CMD ["nginx", "-g", "daemon off;"]