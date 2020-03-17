# this is to grab the alpine image of node v8
FROM node:8-alpine

# this is to set the working directory once the framework is set up in the Docker container
WORKDIR /usr/src/app

# We’ll be copying the package.json & package-lock.json into 
# the container rather than copying entire /node-modules into the directory.
COPY package*.json ./

# installs all the required dependencies such as express mongo & express
RUN npm install

# copies all the contents of your project into the image
# don't forget to pay attention to .dockerignore
COPY . .

# exposes port 8080 for external sources to communicate with our application
EXPOSE 8080

# starts the server
CMD ["npm", "start"]